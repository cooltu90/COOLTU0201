package core.tools;

import android.app.Activity;
import android.content.Intent;

public class ActStartForApplib {
    public static final void test1Activity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu.lib.ui.Test1Activity.class);
        intent.putExtra(PassForApplib.FROM_ACT, act.getClass().getCanonicalName());
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4RequestForApplib.TEST1_ACTIVITY);
    }
    public static final void test3Activity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu.lib.ui.Test3Activity.class);
        intent.putExtra(PassForApplib.FROM_ACT, act.getClass().getCanonicalName());
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4RequestForApplib.TEST3_ACTIVITY);
    }
    public static final void test2Activity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu.lib.ui.Test2Activity.class);
        intent.putExtra(PassForApplib.FROM_ACT, act.getClass().getCanonicalName());
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4RequestForApplib.TEST2_ACTIVITY);
    }

}
