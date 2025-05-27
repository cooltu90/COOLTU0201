package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.dm.Cache;
import com.codingtu.cooltu.processor.builder.base.CacheBuilderBase;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.VariableElement;

public class CacheBuilder extends CacheBuilderBase {

    public static CacheBuilder BUILDER = new CacheBuilder();
    public BaseEs<VariableElement> veTs = Es.es();

    public CacheBuilder() {
        super(CurrentPath.javaInfo(FullName.CACHE_DM));
    }


    public void add(VariableElement ve) {
        veTs.add(ve);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_TOOLS);

        veTs.ls(new Es.EachEs<VariableElement>() {
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


                    String methodName = ConvertTool.toClassType(fieldKv.v);

                    if (StringTool.isBlank(key)) {
                        addLnTag(methods, "");
                        addLnTag(methods, "    public static void cache[Weather](Destroys destroys, [Weather] [weather]) {", methodName, fieldKv.k, fieldKv.v);
                        addLnTag(methods, "        BaseCacheDM.cache(destroys, \"[weather]\", [weather]);", tag, fieldKv.v);
                        addLnTag(methods, "    }");
                        addLnTag(methods, "");
                        addLnTag(methods, "    public static [Weather] get[Weather]() {", fieldKv.k, methodName);
                        addLnTag(methods, "        return BaseCacheDM.getCache(\"[weather]\");", tag);
                        addLnTag(methods, "    }");

                    } else {
                        addLnTag(methods, "    public static void cache[User](Destroys destroys, String [userId], [User] [user]) {\n" +
                                        "        BaseCacheDM.cache(destroys, \"[user]\" + [userId], [user]);\n" +
                                        "    }\n" +
                                        "\n" +
                                        "    public static [User] get[User](String [userId]) {\n" +
                                        "        return BaseCacheDM.getCache(\"[user]\" + [userId]);\n" +
                                        "    }",
                                methodName, key, fieldKv.k, fieldKv.v,
                                tag, key, fieldKv.v,
                                fieldKv.k, methodName, key,
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

public class CacheDM {

[[methods]]
}
model_temp_end */