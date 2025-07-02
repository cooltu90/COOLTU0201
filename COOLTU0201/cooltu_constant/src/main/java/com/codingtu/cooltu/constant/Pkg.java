package com.codingtu.cooltu.constant;

public class Pkg {

    public static String R;
    //public static String ACT;
    public static String CORE = "core";

    //default
    public static final String DEFAULT_ADAPTER = ".app.view.adapter";
    public static final String DEFAULT_UI = ".app.view.ui";

    //processor
    public static final String PROCESSOR = "com.codingtu.cooltu.processor";
    public static final String PROCESSOR_BUILDER_BASE = PROCESSOR + ".builder.base";
    public static final String PROCESSOR_BUILDER_IMPL = PROCESSOR + ".builder.impl";
    public static final String PROCESSOR_BUILDER_CORE = PROCESSOR + ".builder.core";

    //lib4a
    public static final String LIB4A = "com.codingtu.cooltu.lib4a";
    public static final String LIB4A_PATH = LIB4A + ".path";
    public static final String LIB4A_NET = LIB4A + ".net";
    public static final String LIB4A_PERMISSION = LIB4A + ".permission";
    public static final String LIB4A_TOOLS = LIB4A + ".tools";
    public static final String LIB4A_FORM = LIB4A + ".form";
    public static final String LIB4A_FORM_PUSH = LIB4A_FORM + ".push";
    public static final String LIB4A_VIEW = LIB4A + ".view";
    public static final String LIB4A_DIALOG_VIEW = LIB4A_VIEW + ".dialogview";
    public static final String LIB4A_LAYER_VIEW = LIB4A_VIEW + ".layerview";
    public static final String LIB4A_LAYER = LIB4A_VIEW + ".layer";
    public static final String LIB4A_TASK = LIB4A + ".task.task";
    public static final String LIB4A_TASK_RESULT = LIB4A_TASK + ".result";
    public static final String LIB4A_TASK_RUN = LIB4A_TASK + ".run";
    //lib4j
    public static final String LIB4J = "com.codingtu.cooltu.lib4j";
    public static final String LIB4J_TS = LIB4J + ".ts";
    public static final String LIB4J_JSON = LIB4J + ".json";
    public static final String LIB4J_TOOLS = LIB4J + ".tools";
    public static final String LIB4J_DESTORY = LIB4J + ".destory";
    public static final String LIB4J_FUNCTION = LIB4J + ".function";
    //processor_annotation
    public static final String ANNOTATION = "com.codingtu.cooltu.processor.annotation";
    public static final String ANNOTATION_RES = ANNOTATION + ".res";
    public static final String ANNOTATION_UI = ANNOTATION + ".ui";
    public static final String ANNOTATION_TOOLS = ANNOTATION + ".tools";

    //core
    public static final String ACT_BASE() {
        return CORE + ".actbase";
    }

    public static final String FRAGMENT_BASE() {
        return CORE + ".fragmentbase";
    }

    public static final String CORE_TOOLS() {
        return CORE + ".tools";
    }

    public static final String CORE_DATA() {
        return CORE + ".data";
    }

    public static final String ACT_RES() {
        return CORE + ".actres";
    }

    public static final String FRAGMENT_RES() {
        return CORE + ".fragmentres";
    }

    public static final String CORE_NET() {
        return CORE + ".net";
    }

    public static final String CORE_VH() {
        return CORE + ".vh";
    }

    public static final String CORE_CACHE() {
        return CORE + ".cache";
    }

    public static final String CORE_MSTHREAD() {
        return CORE + ".msthread";
    }

    public static final String CORE_NET_API() {
        return CORE_NET() + ".api";
    }

    public static final String CORE_NET_PARAMS() {
        return CORE_NET() + ".params";
    }

    public static final String CORE_NET_BACK() {
        return CORE_NET() + ".back";
    }

}
