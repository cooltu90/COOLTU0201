package com.codingtu.cooltu.tools;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Environment;

import com.codingtu.cooltu.App;
import com.codingtu.cooltu.lib4a.permission.PermissionTool;
import com.codingtu.cooltu.lib4a.tools.VersionTool;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.es.impl.StringEs;

public class PermissionTools extends PermissionTool {

    @SuppressLint("NewApi")
    public static boolean isAllow() {

        if (VersionTool.isGreaterOrEqual(VersionTool.A8)) {
            if (!App.APP.getPackageManager().canRequestPackageInstalls()) {
                return false;
            }
        }

        if (VersionTool.isGreaterOrEqual(VersionTool.A11)) {
            if (!Environment.isExternalStorageManager()) {
                return false;
            }
        }

        return isAllowOthers();
    }

    public static boolean isAllowOthers() {
        StringEs strEs = Es.strs(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE);

        if (VersionTool.isGreaterOrEqual(VersionTool.A12)) {
            strEs.add(Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT);
        }

        return allow(strEs.toArray());
    }

}
