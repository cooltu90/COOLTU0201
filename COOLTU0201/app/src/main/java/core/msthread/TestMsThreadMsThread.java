package core.msthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.codingtu.cooltu.lib4a.msthread.CoreMultiMsThread;

public class TestMsThreadMsThread extends CoreMultiMsThread {

    ///////////////////////////////////////////////////////
    //
    // 创建方法
    //
    ///////////////////////////////////////////////////////
    private Handler mainHandler;
    private Handler subHandler0;

    public void start() {
        createMainHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                createSubHandler0();
            }
        }).start();

    }

    private void createMainHandler() {
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInMain(msg);
            }
        };
    }

    private void createSubHandler0() {
        Looper.prepare();
        subHandler0 = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInThread0(msg);
            }
        };
        sendMessage(subHandler0, subThread0StartType(), 0l);
        Looper.loop();
    }

    public void stop() {
        if (subHandler0 != null) {
            subHandler0.getLooper().quitSafely();
            subHandler0 = null;
        }
        mainHandler = null;


    }
    ///////////////////////////////////////////////////////
    //
    // 初始化方法
    //
    ///////////////////////////////////////////////////////
    private TestMsThreadMsThreadInterface dealer;

    public static TestMsThreadMsThread obtain() {
        return new TestMsThreadMsThread();
    }

    public TestMsThreadMsThread dealer(TestMsThreadMsThreadInterface dealer) {
        this.dealer = dealer;
        return this;
    }

    private int type(TestMsThreadMsThreadType type) {
        return type.ordinal();
    }

    protected boolean isSubThread0() {
        return Thread.currentThread() == subHandler0.getLooper().getThread();
    }


    ///////////////////////////////////////////////////////
    //
    // 主线程的消息处理
    //
    ///////////////////////////////////////////////////////
    private void handleMessageInMain(Message msg) {
        if (msg.what == type(TestMsThreadMsThreadType.ERROR_0)) {
            dealer.error((java.lang.String) msg.obj);
            return;
        }

    }

    public boolean sendMessageForError(java.lang.String msg) {
        if (!isMainThread()) {
            sendMessage(mainHandler, type(TestMsThreadMsThreadType.ERROR_0), 0l, msg);
            return true;
        }
        return false;
    }

    public void sendMessageForErrorForce(java.lang.String msg) {
        sendMessage(mainHandler, type(TestMsThreadMsThreadType.ERROR_0), 0l, msg);
    }


    ///////////////////////////////////////////////////////
    //
    // 线程0的消息处理
    //
    ///////////////////////////////////////////////////////
    private int subThread0StartType() {
        return type(TestMsThreadMsThreadType.SUB_THREAD_START_0);
    }

    private void handleMessageInThread0(Message msg) {
        if (msg.what == type(TestMsThreadMsThreadType.SUB_THREAD_START_0)) {
            dealer.subThreadStart();
            return;
        }
        if (msg.what == type(TestMsThreadMsThreadType.TOAST1_0)) {
            dealer.toast1();
            return;
        }
    }

    public boolean sendMessageForSubThreadStart() {
        if (!isSubThread0()) {
            sendMessage(subHandler0, type(TestMsThreadMsThreadType.SUB_THREAD_START_0), 0l);
            return true;
        }
        return false;
    }

    public void sendMessageForSubThreadStartForce() {
        sendMessage(subHandler0, type(TestMsThreadMsThreadType.SUB_THREAD_START_0), 0l);
    }

    public boolean sendMessageForToast1() {
        if (!isSubThread0()) {
            sendMessage(subHandler0, type(TestMsThreadMsThreadType.TOAST1_0), 1000l);
            return true;
        }
        try {
            Thread.sleep(1000l);
        } catch (Exception e) {
        }
        return false;
    }

    public void sendMessageForToast1Force() {
        sendMessage(subHandler0, type(TestMsThreadMsThreadType.TOAST1_0), 1000l);
    }


}

