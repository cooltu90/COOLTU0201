package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.processor.builder.impl.CacheBuilder;
import com.codingtu.cooltu.processor.deal.base.FieldBaseDeal;

import javax.lang.model.element.VariableElement;

public class CacheDeal extends FieldBaseDeal {
    @Override
    protected void dealFieldElement(VariableElement ve) {
        CacheBuilder.BUILDER.add(ve);
    }
}
