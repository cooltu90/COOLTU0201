package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.dm.Cache;
import com.codingtu.cooltu.processor.builder.base.CacheDMBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.List;

import javax.lang.model.element.VariableElement;

public class CacheDMBuilder extends CacheDMBuilderBase {

    private BaseEs<VariableElement> veEs = Es.es();

    public CacheDMBuilder(JavaInfo info) {
        super(info);
    }

    public void addVe(VariableElement ve) {
        veEs.add(ve);
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected boolean isForce() {
        return true;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        Logs.i(lines);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_CACHE);
        addTag(className, javaInfo.name);

        veEs.ls(new Es.EachEs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {

                Cache cache = ve.getAnnotation(Cache.class);
                if (cache != null) {
                    String tag = cache.tag();
                    String key = cache.key();
                    KV<String, String> fieldKv = ElementTools.getFieldKv(ve);
                    if (StringTool.isBlank(tag)) {
                        tag = fieldKv.v;
                    }

                    tag = javaInfo.name + "_" + tag;

                    String methodName = fieldKv.v;

                    if (StringTool.isBlank(key)) {
                        addLnTag(methods, "");
                        addLnTag(methods, "    public static void [Weather]([Weather] [weather]) {", methodName, fieldKv.k, fieldKv.v);
                        addLnTag(methods, "        BaseCacheDM.cache(\"[weather]\", [weather]);", tag, fieldKv.v);
                        addLnTag(methods, "    }");
                        addLnTag(methods, "");
                        addLnTag(methods, "    public static [Weather] [Weather]() {", fieldKv.k, methodName);
                        addLnTag(methods, "        return BaseCacheDM.getCache([xx].class, \"[weather]\");", fieldKv.k, tag);
                        addLnTag(methods, "    }");

                    } else {
                        addLnTag(methods, "    public static void [User](Destroys destroys, String [userId], [User] [user]) {\n" +
                                        "        BaseCacheDM.cache(destroys, \"[user]\" + [userId], [user]);\n" +
                                        "    }\n" +
                                        "\n" +
                                        "    public static [User] [User](String [userId]) {\n" +
                                        "        return BaseCacheDM.getCache([xx].class, \"[user]\" + [userId]);\n" +
                                        "    }",
                                methodName, key, fieldKv.k, fieldKv.v,
                                tag, key, fieldKv.v,
                                fieldKv.k, methodName, key, fieldKv.k,
                                tag, key
                        );
                    }
                }

                return false;
            }
        });
    }

}
/* model_temp_start
package [[pkg]];

import com.codingtu.cooltu.lib4a.dm.BaseCacheDM;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class [[className]] {

[[methods]]
}
model_temp_end */