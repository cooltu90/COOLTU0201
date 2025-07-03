package com.codingtu.cooltu.lib.ui;

import com.codingtu.cooltu.lib.R;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.Test3ActivityBase;
import core.actres.Test3ActivityRes;

@To(Test3ActivityRes.class)
@ActBase(layoutName = "activity_test3")
public class Test3Activity extends Test3ActivityBase {
    @Override
    protected int getLayout() {
        return R.layout.activity_test3;
    }

}

