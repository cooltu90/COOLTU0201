package com.codingtu.cooltu.lib.ui;

import com.codingtu.cooltu.lib.R;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.FragmentBase;

import core.fragmentbase.Test1FragmentBase;
import core.fragmentres.Test1FragmentRes;

@To(Test1FragmentRes.class)
@FragmentBase(layoutName = "fragment_test1")
public class Test1Fragment extends Test1FragmentBase {

    @Override
    protected int getLayout() {
        return R.layout.fragment_test1;
    }
}
