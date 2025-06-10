package com.codingtu.cooltu.ud;

import com.codingtu.cooltu.processor.annotation.msthread.MainThread;
import com.codingtu.cooltu.processor.annotation.msthread.MsThread;
import com.codingtu.cooltu.processor.annotation.msthread.SubThread;

import core.msthread.TestMsThreadBaseForMsThread;

@MsThread
public class TestMsThread extends TestMsThreadBaseForMsThread {


    @SubThread(isStart = true)
    public void subThreadStart() {
        if (sendMessageForSubThreadStart()) {
            return;
        }
    }

    @SubThread(isDelay = true, defaultDelayMillis = 1000)
    public void toast1() {

    }

    @MainThread
    public void error(String msg){

    }

}
