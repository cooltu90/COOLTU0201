package com.codingtu.cooltu.lib.ui;

import android.Manifest;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.codingtu.cooltu.lib.R;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.Permission;

import core.actbase.Test1ActivityBase;
import core.actres.Test1ActivityRes;

@To(Test1ActivityRes.class)
@ActBase(layoutName = "activity_test1")
public class Test1Activity extends Test1ActivityBase {

    @Override
    protected int getLayout() {
        return R.layout.activity_test1;
    }


    @Permission({
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    })
    protected void check() {

    }
}

