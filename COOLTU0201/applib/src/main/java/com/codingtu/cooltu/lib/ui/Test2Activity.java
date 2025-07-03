package com.codingtu.cooltu.lib.ui;

import com.codingtu.cooltu.lib.R;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.Test2ActivityBase;
import core.actres.Test2ActivityRes;

@To(Test2ActivityRes.class)
@ActBase(layoutName = "activity_test2")
public class Test2Activity extends Test2ActivityBase {
    @Override
    protected int getLayout() {
        return R.layout.activity_test2;
    }

}

