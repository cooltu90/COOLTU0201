package com.codingtu.cooltu;

import com.codingtu.cooltu.lib4a.CoreConfigs;

public class Configs extends CoreConfigs {
    @Override
    public String getBaseUrl() {
        return null;
    }

    @Override
    public String getImageGetterFileProvider() {
        return "com.codingtu.cooltu.fileprovider";
    }

    @Override
    public boolean isLog() {
        return true;
    }

    @Override
    public boolean isLogHttpConnect() {
        return false;
    }

    @Override
    public boolean isLogJsonException() {
        return false;
    }

    @Override
    public String getDefaultLogTag() {
        return "TestApp";
    }

    //


    @Override
    public int getDialogLayout() {
        return super.getDialogLayout();
    }

    @Override
    public int getToastDialogLayout() {
        return R.layout.dialog_toast;
    }

    @Override
    public int getNoticeDialogLayout() {
        return super.getNoticeDialogLayout();
    }

    @Override
    public int getEditDialogLayout() {
        return super.getEditDialogLayout();
    }

    @Override
    public int getMenuDialogLayout() {
        return super.getMenuDialogLayout();
    }

    @Override
    public int getMenuDialogItemLayout() {
        return super.getMenuDialogItemLayout();
    }
}
