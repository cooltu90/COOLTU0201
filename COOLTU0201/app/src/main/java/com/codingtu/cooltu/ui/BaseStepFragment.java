package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.processor.annotation.msthread.MainThread;
import com.codingtu.cooltu.processor.annotation.msthread.MsThread;
import com.codingtu.cooltu.processor.annotation.msthread.SubThread;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.FragmentBase;
import com.codingtu.cooltu.processor.annotation.ui.LongClickView;

import core.fragmentbase.BaseStepFragmentBase;
import core.fragmentres.BaseStepFragmentRes;

@MsThread
@To(BaseStepFragmentRes.class)
@FragmentBase
public class BaseStepFragment extends BaseStepFragmentBase {

    @ClickView(R.id.tv2)
    public void tv2Click() {

    }

    @LongClickView(R.id.tv2)
    public boolean tv2LongClick() {
        return true;
    }

    @SubThread(isStart = true)
    public void subThreadStart(){

    }

    @SubThread
    public void method1(String str){

    }

    @MainThread
    public void method2(){

    }

}
