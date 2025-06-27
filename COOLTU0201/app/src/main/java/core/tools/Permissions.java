package core.tools;

import android.app.Activity;

public class Permissions {
    public static final int CODE_CHECK_IN_BASE_WELCOME_ACTIVITY = 0;
    public static final int CODE_CHECK_IN_PERMISSION_ACTIVITY = 1;

    public static void checkInBaseWelcomeActivity(Activity act) {
        com.codingtu.cooltu.lib4a.permission.PermissionTool.check(act, CODE_CHECK_IN_BASE_WELCOME_ACTIVITY
                , "android.permission.WRITE_EXTERNAL_STORAGE"
        );
    }
    public static void checkInPermissionActivity(Activity act) {
        com.codingtu.cooltu.lib4a.permission.PermissionTool.check(act, CODE_CHECK_IN_PERMISSION_ACTIVITY
                , "android.permission.WRITE_EXTERNAL_STORAGE"
                , "android.permission.CAMERA"
                , "android.permission.ACCESS_FINE_LOCATION"
                , "android.permission.ACCESS_COARSE_LOCATION"
                , "android.permission.ACCESS_WIFI_STATE"
                , "android.permission.CHANGE_WIFI_STATE"
                , "android.permission.BLUETOOTH_SCAN"
                , "android.permission.BLUETOOTH_CONNECT"
        );
    }

}
