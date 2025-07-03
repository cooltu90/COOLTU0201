package com.codingtu.cooltu.lib.ui;

import com.codingtu.cooltu.lib.R;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.FragmentBase;

import core.fragmentbase.Test2FragmentBase;
import core.fragmentres.Test2FragmentRes;

@To(Test2FragmentRes.class)
@FragmentBase(layoutName = "fragment_test2")
public class Test2Fragment extends Test2FragmentBase {
    @Override
    protected int getLayout() {
        return R.layout.fragment_test2;
    }

}
