package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;

public class BuilderTools {

    public static String moduleSuffix() {
        if (Module.CURRENT.endsWith(Module.APP)) {
            return "";
        } else {
            return "For" + ConvertTool.toClassType(Module.CURRENT);
        }
    }
}
