package com.codingtu.cooltu.processor.annotation.ui;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface DefaultMenuDialogLayout {
    int layout();

    int item();
}