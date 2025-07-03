package com.codingtu.cooltu.lib;

import com.codingtu.cooltu.lib4a.ui.act.CoreActivity;
import com.codingtu.cooltu.lib4a.ui.fragment.CoreFragment;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.annotation.create.CreateFragment;

@ModuleInfo(
        module = "applib",
        baseAct = CoreActivity.class,
        baseFragment = CoreFragment.class,
        rPkg = "com.codingtu.cooltu.lib"
)
@CreateAct(
        name = "test3",
        packages = "com.codingtu.cooltu.lib.ui",
        baseClass = CoreActivity.class,
        layoutTempName = "layout_temp"
)
@CreateFragment(
        name = "test2",
        packages = "com.codingtu.cooltu.lib.ui",
        baseClass = CoreFragment.class,
        layoutTempName = "layout_temp"
)
public class AppLibConfig {
}
