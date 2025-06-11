package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.processor.builder.impl.DataBuilder;
import com.codingtu.cooltu.processor.deal.base.MethodBaseDeal;

import javax.lang.model.element.ExecutableElement;

public class DataDeal extends MethodBaseDeal {

    @Override
    protected void dealMethodElement(ExecutableElement ee) {
        DataBuilder.BUILDER.addMethod(ee);
    }
}
