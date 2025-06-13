package com.codingtu.cooltu.lib4a.task.service;

import android.content.Intent;

import com.codingtu.cooltu.lib4a.CoreApp;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.task.task.Task;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;

public class TaskService extends TaskServiceBaseForMsThread<TaskService> {

    public static final String TAG = "TASKSERVICE";

    public static TaskService SERVICE;
    public static int SERVICE_STATUS;
    public static int APP_TASK_ID;
    public static final int APP_TASK_MAX_ID = 10000;

    private static BaseEs<Task> MAIN_TASK_ES;
    private static BaseEs<Task> BACKGROUND_TASK_ES;

    public static int obtainAppTaskId() {
        if (APP_TASK_ID > APP_TASK_MAX_ID) {
            APP_TASK_ID = 0;
        }
        return APP_TASK_ID++;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SERVICE = this;
        SERVICE_STATUS = 0;
        start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMsThread();
        SERVICE = null;
    }

    public void mainSubThreadStart() {
        dealMainSubThread();
    }

    public void backgroundSubThreadStart() {
        dealBackgroundSubThread();
    }

    public void dealMainSubThread() {
        if (sendMessageForDealMainSubThread()) {
            return;
        }
        try {
            taskRun(MAIN_TASK_ES);
        } catch (Exception e) {
            Logs.e(TAG, "thread error");
            Logs.e(TAG, e);
        }
        sendMessageForDealMainSubThreadForce();
    }

    public void dealBackgroundSubThread() {
        if (sendMessageForDealBackgroundSubThread()) {
            return;
        }
        try {
            taskRun(BACKGROUND_TASK_ES);
        } catch (Exception e) {
            Logs.e(TAG, "thread error");
            Logs.e(TAG, e);
        }
        sendMessageForDealBackgroundSubThreadForce();
    }

    private void taskRun(BaseEs<Task> taskEs) {
        Task task = getTask(taskEs);
        if (task != null) {
            try {
                Logs.i(TAG, "app任务运行：" + task.id + " " + Thread.currentThread().getName());
                task.taskRun.run(task.p0(), task.p1(), task.p2(), task.p3(), task.p4(), task.p5(), task.p6(), task.p7(), task.p8());
            } catch (Exception e) {
                if (task.error != null) {
                    taskError(task, e);
                }
                return;
            }

            if (task.taskResult != null) {
                taskResult(task);
            }
        }
    }

    private static synchronized Task getTask(BaseEs<Task> taskEs) {
        if (taskEs != null) {
            Task task = taskEs.getByIndex(0);
            if (task != null) {
                taskEs.deleteByIndex(0);
                return task;
            }
        }
        return null;
    }

    public void taskResult(Task task) {
        if (sendMessageForTaskResult(task)) {
            return;
        }
        Logs.i(TAG, "taskResult:" + task.id);
        task.taskResult.result(task.r0(), task.r1(), task.r2(), task.r3(), task.r4(), task.r5(), task.r6(), task.r7(), task.r8());
    }

    public void taskError(Task task, Exception e) {
        if (sendMessageForTaskError(task, e)) {
            return;
        }
        task.error.onError(e);
    }

    /**************************************************
     *
     **************************************************/
    public static void addTask(Task task) {
        if (task.isBackgroundSub) {
            addToBackground(task);
        } else {
            addToMain(task);
        }

        if (SERVICE == null && SERVICE_STATUS == 0) {
            SERVICE_STATUS = 1;
            //没有service
            Intent intent = new Intent(CoreApp.APP, TaskService.class);
            CoreApp.APP.startService(intent);
        }
    }

    private static synchronized void addToMain(Task task) {
        if (MAIN_TASK_ES == null) {
            MAIN_TASK_ES = Es.es();
        }
        addToEs(MAIN_TASK_ES, task);
        MAIN_TASK_ES.add(task);
    }

    private static synchronized void addToBackground(Task task) {
        if (BACKGROUND_TASK_ES == null) {
            BACKGROUND_TASK_ES = Es.es();
        }
        addToEs(BACKGROUND_TASK_ES, task);
        BACKGROUND_TASK_ES.add(task);
    }

    private static void addToEs(BaseEs<Task> taskTs, Task task) {
        if (task.taskRun.taskCover != null) {
            taskTs.deleteAll(new Es.IsThisOne<Task>() {
                @Override
                public boolean isThisOne(int position, Task tTask) {
                    return task.type == tTask.type && task.taskRun.taskCover.cover(
                            task.p0(), tTask.p0(),
                            task.p1(), tTask.p1(),
                            task.p2(), tTask.p2(),
                            task.p3(), tTask.p3(),
                            task.p4(), tTask.p4(),
                            task.p5(), tTask.p5(),
                            task.p6(), tTask.p6(),
                            task.p7(), tTask.p7(),
                            task.p8(), tTask.p8()
                    );
                }
            });
        }
    }

}
