package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4a.task.task.TaskDM;
import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover2;
import com.codingtu.cooltu.lib4a.task.task.result.TaskResult1;
import com.codingtu.cooltu.lib4a.task.task.run.TaskRun1;
import com.codingtu.cooltu.lib4a.task.task.run.TaskRun2;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.ui.base.BaseWelcomeActivity;

import core.actbase.WelcomeActivityBase;
import core.actres.WelcomeActivityRes;
import core.cache.NowCreateDocDM;

@To(WelcomeActivityRes.class)
@ToRes(R.layout.activity_welcome)
@ActBase(base = BaseWelcomeActivity.class)
public class WelcomeActivity extends WelcomeActivityBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //CacheDM.cacheName(this, "xx","xxx");
    }


    @ClickView(R.id.showBt)
    public void showBtClick() {

        for (int i = 0; i < 10000; i++) {
            TaskDM.task(i).run(new TaskRun1<Integer>() {
                @Override
                public void run(Integer integer) {
                    Logs.i("main:" + integer + " " + Thread.currentThread().getName());
                    addResult(integer);
                    TaskDM.task(0, integer).run(new TaskRun2<Integer, Integer>() {
                        @Override
                        public void run(Integer integer, Integer integer2) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Logs.i("sub" + integer2 + " " + Thread.currentThread().getName());
                        }
                    }).cover(new TaskCover2<Integer, Integer>() {
                        @Override
                        public boolean cover(Integer cp0, Integer tp0, Integer cp1, Integer tp1) {
                            return cp0 == tp0;
                        }
                    }).result().type(1).background().add();
                }
            }).result(new TaskResult1<Integer>() {
                @Override
                public void result(Integer integer) {
                    Logs.i("main result:" + integer);
                }
            }).add();
        }
    }


    @Override
    protected boolean editDialogYes(String text) {
        return true;
    }

    @ClickView(value = R.id.reportTv, inAct = false)
    public void reportTvClick() {
        toast("clickReport");
    }

    @Override
    protected TestCallBack testCallBackInit() {
        return new TestCallBack() {
            @Override
            public void callback() {

            }
        };
    }

    @NetBack
    public void addObj1Back(String json) {

    }

}
