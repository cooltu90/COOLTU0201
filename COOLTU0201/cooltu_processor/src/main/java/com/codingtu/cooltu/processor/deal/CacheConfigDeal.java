package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.dm.CacheConfig;
import com.codingtu.cooltu.processor.builder.impl.CacheDMBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class CacheConfigDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        CacheConfig cacheConfig = te.getAnnotation(CacheConfig.class);
        String cacheDmName = cacheConfig.value();
        if (StringTool.isBlank(cacheDmName)) {
            cacheDmName = ElementTools.simpleName(te);
        }
        Logs.i("cacheDmName:" + cacheDmName);

        JavaInfo cacheDmJavaInfo = CurrentPath.javaInfo(Pkg.CORE_CACHE(), cacheDmName);
        CacheDMBuilder cacheDMBuilder = new CacheDMBuilder(cacheDmJavaInfo);


        ElementTools.getVariableElements(te).ls(new Es.EachEs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                cacheDMBuilder.addVe(ve);
                return false;
            }
        });

    }

}
