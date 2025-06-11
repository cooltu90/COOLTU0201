package core.data;

public class DataDM {
    public static void user(java.lang.String name, 
                            com.codingtu.cooltu.lib4a.task.task.result.TaskResult1<com.codingtu.cooltu.bean.User> taskResult) {
        com.codingtu.cooltu.lib4a.task.task.TaskDM
                .task(name)
                .run(new com.codingtu.cooltu.lib4a.task.task.run.TaskRun1<java.lang.String>() {
                    @Override
                    public void run(java.lang.String name) {
                        addResult(new com.codingtu.cooltu.dm.UserDataConfig().user(name));
                    }
                })
                .result(taskResult)
                .error(new com.codingtu.cooltu.lib4j.function.OnError() {
                    @Override
                    public void onError(Throwable throwable) {
                        if (taskResult != null) {
                            taskResult.result(null);
                        }
                    }
                }).add();
    }
    public static void user(com.codingtu.cooltu.lib4a.task.task.result.TaskResult1<com.codingtu.cooltu.bean.User> taskResult) {
        com.codingtu.cooltu.lib4a.task.task.TaskDM
                .task()
                .run(new com.codingtu.cooltu.lib4a.task.task.run.TaskRun0() {
                    @Override
                    public void run() {
                        addResult(new com.codingtu.cooltu.dm.UserDataConfig().user());
                    }
                })
                .result(taskResult)
                .error(new com.codingtu.cooltu.lib4j.function.OnError() {
                    @Override
                    public void onError(Throwable throwable) {
                        if (taskResult != null) {
                            taskResult.result(null);
                        }
                    }
                }).add();
    }
    public static void user(java.lang.String name, int age, 
                            com.codingtu.cooltu.lib4a.task.task.result.TaskResult1<com.codingtu.cooltu.bean.User> taskResult) {
        com.codingtu.cooltu.lib4a.task.task.TaskDM
                .task(name, age)
                .run(new com.codingtu.cooltu.lib4a.task.task.run.TaskRun2<java.lang.String, java.lang.Integer>() {
                    @Override
                    public void run(java.lang.String name, java.lang.Integer age) {
                        addResult(new com.codingtu.cooltu.dm.UserDataConfig().user(name, age));
                    }
                })
                .result(taskResult)
                .error(new com.codingtu.cooltu.lib4j.function.OnError() {
                    @Override
                    public void onError(Throwable throwable) {
                        if (taskResult != null) {
                            taskResult.result(null);
                        }
                    }
                }).add();
    }

}
