package core.tools;

import android.app.Activity;

public class PermissionsForApplib {
    public static final int CODE_CHECK_IN_TEST1_ACTIVITY = 0;

    public static void checkInTest1Activity(Activity act) {
        com.codingtu.cooltu.lib4a.permission.PermissionTool.check(act, CODE_CHECK_IN_TEST1_ACTIVITY
                , "android.permission.WRITE_EXTERNAL_STORAGE"
        );
    }

}
