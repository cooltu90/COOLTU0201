package com.codingtu.cooltu.ui;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.KeyEvent;

import androidx.annotation.RequiresApi;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.tools.AppJumpTool;
import com.codingtu.cooltu.lib4a.tools.VersionTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.uicore.WhenBackKeyDown;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.tools.PermissionTools;

import core.actbase.PermissionActivityBase;
import core.actres.PermissionActivityRes;
import core.tools.Code4Request;
import core.tools.Permissions;

@To(PermissionActivityRes.class)
@ActBase(layout = R.layout.activity_permission)
public class PermissionActivity extends PermissionActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBase().addWhenKeyDown(new WhenBackKeyDown() {
            @Override
            public boolean onBack(KeyEvent event) {
                return true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onResume() {
        super.onResume();
        if (VersionTool.isGreaterOrEqual(VersionTool.A11)) {
            ViewTool.visible(version11Ll);
            if (Environment.isExternalStorageManager()) {
                ViewTool.setText(version11Bt, "已授权");
            } else {
                ViewTool.setText(version11Bt, "未授权");
            }
        } else {
            ViewTool.gone(version11Ll);
        }

        if (VersionTool.isGreaterOrEqual(VersionTool.A8)) {
            ViewTool.visible(installLl);
            if (getPackageManager().canRequestPackageInstalls()) {
                ViewTool.setText(installBt, "已授权");
            } else {
                ViewTool.setText(installBt, "未授权");
            }
        } else {
            ViewTool.gone(installLl);
        }

        ViewTool.setText(storeTv, PermissionTools.allow(Manifest.permission.WRITE_EXTERNAL_STORAGE) ? "已授权" : "未授权");
        ViewTool.setText(cameraTv, PermissionTools.allow(Manifest.permission.CAMERA) ? "已授权" : "未授权");
        ViewTool.setText(locationTv, PermissionTools.allow(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION) ? "已授权" : "未授权");

        if (VersionTool.isGreaterOrEqual(VersionTool.A12)) {
            ViewTool.visible(bluetoothLl);
            ViewTool.setText(bluetoothTv,
                    PermissionTools.allow(Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT) ? "已授权" : "未授权");
        } else {
            ViewTool.gone(bluetoothLl);
        }

        if (PermissionTools.isAllow()) {
            check();
        }
    }

    @RequiresApi(api = VersionTool.A11)
    @ClickView(R.id.version11Bt)
    public void version11BtClick() {
        if (!Environment.isExternalStorageManager()) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, Code4Request.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        }
    }

    @ClickView({R.id.storeTv, R.id.cameraTv, R.id.locationTv, R.id.bluetoothTv})
    public void permisstionBtClick() {
        if (!PermissionTools.isAllowOthers()) {
            Permissions.checkInPermissionActivity(getAct());
        }
    }


    @ClickView(R.id.installBt)
    public void installBtClick() {
        AppJumpTool.manageUnknownApp(this, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Permission({
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
    })
    public void check() {
        if (!PermissionTools.isAllow()) {
            onResume();
            return;
        }
        setResultOk();
        finish();
    }

}

