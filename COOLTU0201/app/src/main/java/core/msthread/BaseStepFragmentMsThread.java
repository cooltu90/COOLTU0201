package core.msthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.codingtu.cooltu.lib4a.msthread.CoreMultiMsThread;

public class BaseStepFragmentMsThread extends CoreMultiMsThread {

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
    private BaseStepFragmentMsThreadInterface dealer;

    public static BaseStepFragmentMsThread obtain() {
        return new BaseStepFragmentMsThread();
    }

    public BaseStepFragmentMsThread dealer(BaseStepFragmentMsThreadInterface dealer) {
        this.dealer = dealer;
        return this;
    }

    private int type(BaseStepFragmentMsThreadType type) {
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
        if (msg.what == type(BaseStepFragmentMsThreadType.METHOD2_0)) {
            dealer.method2();
            return;
        }

    }

    public boolean sendMessageForMethod2() {
        if (!isMainThread()) {
            sendMessage(mainHandler, type(BaseStepFragmentMsThreadType.METHOD2_0), 0l);
            return true;
        }
        return false;
    }


    ///////////////////////////////////////////////////////
    //
    // 线程0的消息处理
    //
    ///////////////////////////////////////////////////////
    private int subThread0StartType() {
        return type(BaseStepFragmentMsThreadType.SUB_THREAD_START_0);
    }

    private void handleMessageInThread0(Message msg) {
        if (msg.what == type(BaseStepFragmentMsThreadType.SUB_THREAD_START_0)) {
            dealer.subThreadStart();
            return;
        }
        if (msg.what == type(BaseStepFragmentMsThreadType.METHOD1_0)) {
            dealer.method1((java.lang.String) msg.obj);
            return;
        }
    }

    public boolean sendMessageForSubThreadStart() {
        if (!isSubThread0()) {
            sendMessage(subHandler0, type(BaseStepFragmentMsThreadType.SUB_THREAD_START_0), 0l);
            return true;
        }
        return false;
    }

    public boolean sendMessageForMethod1(java.lang.String str) {
        if (!isSubThread0()) {
            sendMessage(subHandler0, type(BaseStepFragmentMsThreadType.METHOD1_0), 0l, str);
            return true;
        }
        return false;
    }


}

