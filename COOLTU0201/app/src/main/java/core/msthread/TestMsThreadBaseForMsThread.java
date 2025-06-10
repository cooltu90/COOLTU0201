package core.msthread;

public class TestMsThreadBaseForMsThread implements TestMsThreadMsThreadInterface {

    protected TestMsThreadMsThread testMsThreadMsThread;

    public void start() {
        testMsThreadMsThread = TestMsThreadMsThread.obtain().dealer(this);
        testMsThreadMsThread.start();
    }

    @Override
    public void subThreadStart() {
    }

    protected boolean sendMessageForSubThreadStart() {
        if (testMsThreadMsThread != null) {
            return testMsThreadMsThread.sendMessageForSubThreadStart();
        }
        return true;
    }

    protected void sendMessageForSubThreadStartForce() {
        if (testMsThreadMsThread != null) {
            testMsThreadMsThread.sendMessageForSubThreadStartForce();
        }
    }

    @Override
    public void toast1() {
    }

    protected boolean sendMessageForToast1() {
        if (testMsThreadMsThread != null) {
            return testMsThreadMsThread.sendMessageForToast1();
        }
        return true;
    }

    protected void sendMessageForToast1Force() {
        if (testMsThreadMsThread != null) {
            testMsThreadMsThread.sendMessageForToast1Force();
        }
    }

    @Override
    public void error(java.lang.String msg) {
    }

    protected boolean sendMessageForError(java.lang.String msg) {
        if (testMsThreadMsThread != null) {
            return testMsThreadMsThread.sendMessageForError(msg);
        }
        return true;
    }

    protected void sendMessageForErrorForce(java.lang.String msg) {
        if (testMsThreadMsThread != null) {
            testMsThreadMsThread.sendMessageForErrorForce(msg);
        }
    }



    protected void stopMsThread() {
        if (testMsThreadMsThread != null)
            testMsThreadMsThread.stop();
        testMsThreadMsThread = null;
    }

}
