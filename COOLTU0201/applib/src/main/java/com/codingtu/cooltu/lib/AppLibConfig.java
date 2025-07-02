package com.codingtu.cooltu.lib;

import com.codingtu.cooltu.lib4a.ui.act.CoreActivity;
import com.codingtu.cooltu.lib4a.ui.fragment.CoreFragment;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;

@ModuleInfo(
        module = "applib",
        baseAct = CoreActivity.class,
        baseFragment = CoreFragment.class,
        rPkg = "com.codingtu.cooltu.lib"
)
public class AppLibConfig {
}
