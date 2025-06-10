package core.msthread;

public class TesUdBaseForMsThread<THIS extends TesUdBaseForMsThread> extends com.codingtu.cooltu.test.TestUdBase<THIS> implements TesUdMsThreadInterface {

    protected TesUdMsThread tesUdMsThread;

    public void start() {
        tesUdMsThread = TesUdMsThread.obtain().dealer(this);
        tesUdMsThread.start();
    }

    @Override
    public void subStart() {
    }

    protected boolean sendMessageForSubStart() {
        if (tesUdMsThread != null) {
            return tesUdMsThread.sendMessageForSubStart();
        }
        return true;
    }

    protected void sendMessageForSubStartForce() {
        if (tesUdMsThread != null) {
            tesUdMsThread.sendMessageForSubStartForce();
        }
    }

    @Override
    public void toast(java.lang.String msg) {
    }

    protected boolean sendMessageForToast(java.lang.String msg) {
        if (tesUdMsThread != null) {
            return tesUdMsThread.sendMessageForToast(msg);
        }
        return true;
    }

    protected void sendMessageForToastForce(java.lang.String msg) {
        if (tesUdMsThread != null) {
            tesUdMsThread.sendMessageForToastForce(msg);
        }
    }



    protected void stopMsThread() {
        if (tesUdMsThread != null)
            tesUdMsThread.stop();
        tesUdMsThread = null;
    }

}
