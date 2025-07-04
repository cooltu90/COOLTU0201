package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4a.function.OnProgressInUiThread;
import com.codingtu.cooltu.lib4a.task.task.TaskDM;
import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover2;
import com.codingtu.cooltu.lib4a.task.task.result.TaskResult1;
import com.codingtu.cooltu.lib4a.task.task.run.TaskRun1;
import com.codingtu.cooltu.lib4a.task.task.run.TaskRun2;
import com.codingtu.cooltu.lib4a.tools.SDCardTool;
import com.codingtu.cooltu.lib4j.file.delete.FileDeleter;
import com.codingtu.cooltu.lib4j.function.OnFinish;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.tools.PermissionTools;
import com.codingtu.cooltu.ui.base.BaseWelcomeActivity;

import core.actbase.WelcomeActivityBase;
import core.actres.WelcomeActivityRes;
import core.cache.NowCreateDocDM;
import core.tools.ActStart;

@To(WelcomeActivityRes.class)
@ToRes(R.layout.activity_welcome)
@ActBase(base = BaseWelcomeActivity.class)
public class WelcomeActivity extends WelcomeActivityBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //CacheDM.cacheName(this, "xx","xxx");
        if (!PermissionTools.isAllow()) {
            ActStart.permissionActivity(getAct());
        }
    }


    @ClickView(R.id.showBt)
    public void showBtClick() {
        getToastDialog().setContent("xxx").show().start();
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
