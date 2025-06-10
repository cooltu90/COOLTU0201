package com.codingtu.cooltu.lib4a.task.service;

import com.codingtu.cooltu.lib4a.task.task.Task;

public interface TaskServiceMsThreadInterface {

    void mainSubThreadStart();

    void backgroundSubThreadStart();

    void dealMainSubThread();

    void dealBackgroundSubThread();

    void taskResult(Task task);

    void taskError(Task task, Exception e);

}
