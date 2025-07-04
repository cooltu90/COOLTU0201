package com.codingtu.cooltu;

import com.codingtu.cooltu.constant.AdapterType;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.lib4a.CoreApp;
import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.lib4a.ui.fragment.CoreFragment;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.annotation.create.CreateAdapter;
import com.codingtu.cooltu.processor.annotation.ui.DefaultCode;
import com.codingtu.cooltu.ui.base.BaseActivity;

@ModuleInfo(
        module = Module.APP,
        baseAct = BaseActivity.class,
        baseFragment = CoreFragment.class,
        rPkg = "com.codingtu.cooltu"
)
@DefaultCode({"CODE_TEST", "GET_PIC_BY_CAMERA", "GET_PIC_BY_GALLERY", "MANAGE_APP_ALL_FILES_ACCESS_PERMISSION"})
@CreateAct(
        name = "permission",
        packages = "com.codingtu.cooltu.ui",
        baseClass = BaseActivity.class,
        layoutTemp = R.layout.layout_temp
)
@CreateAdapter(
        name = "horse",
        packages = "com.codingtu.cooltu.ui.adapter",
        type = AdapterType.DEFAULT_MORE_LIST,
        layoutTemp = R.layout.layout_temp
)
//@CreateFragment(
//        name = "base_step",
//        packages = "com.codingtu.cooltu.ui",
//        baseClass = CoreFragment.class,
//        layoutTemp = R.layout.layout_temp
//)
public class App extends CoreApp {
    @Override
    public CoreConfigs createConfigs() {
        return new Configs();
    }
}


