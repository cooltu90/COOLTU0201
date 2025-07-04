package com.codingtu.cooltu.lib4a;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.codingtu.cooltu.lib4a.connect.CoreConnectConfigs;
import com.codingtu.cooltu.lib4a.net.retrofit.OkHttpClientCreater;
import com.codingtu.cooltu.lib4a.net.retrofit.RetrofitCreater;
import com.codingtu.cooltu.lib4j.config.LibConfigs;
import com.codingtu.cooltu.lib4j.log.LibLogs;

import okhttp3.OkHttpClient;

public abstract class CoreConfigs extends LibConfigs {

    public static CoreConfigs configs() {
        return (CoreConfigs) LibConfigs.configs();
    }

    @Override
    public void baseLog(int level, String tag, String msg) {
        switch (level) {
            case LibLogs.LEVEL_INFO:
                Log.i(tag, msg);
                break;
            case LibLogs.LEVEL_WARNING:
                Log.w(tag, msg);
                break;
            case LibLogs.LEVEL_ERROR:
                Log.e(tag, msg);
                break;
        }
    }

    public float getDensity() {
        return 360f;
    }

    public OkHttpClientCreater getDefaultOkHttpClientCreater() {
        return null;
    }

    public RetrofitCreater getDefaultRetrofitCreater() {
        return null;
    }

    public void addInterceptorForDefaultOkHttpClient(OkHttpClient.Builder builder) {

    }

    public abstract String getBaseUrl();

    public String getPicDirName() {
        return "DCIM/Camera";
    }

    public abstract String getImageGetterFileProvider();


    public Integer getDefaultIcon() {
        return R.mipmap.default_img;
    }

    public SharedPreferences pf() {
        return PreferenceManager.getDefaultSharedPreferences(CoreApp.APP);
    }

    public CoreConnectConfigs getConnectConfigs() {
        return null;
    }

    public int getOkGoReadTimeout() {
        return 10 * 60 * 1000;
    }

    //dialogs
    public int getDialogLayout() {
        return R.layout.default_dialog;
    }

    public int getToastDialogLayout() {
        return R.layout.default_dialog_toast;
    }

    public int getNoticeDialogLayout() {
        return R.layout.default_dialog_notice;
    }

    public int getEditDialogLayout() {
        return R.layout.default_dialog_edit;
    }

    public int getMenuDialogLayout() {
        return R.layout.default_dialog_menu;
    }

    public int getMenuDialogItemLayout() {
        return R.layout.default_dialog_menu_item;
    }
}
