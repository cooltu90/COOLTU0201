package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.processor.annotation.delete.DeleteAct;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.App;

import javax.lang.model.element.TypeElement;

public class DeleteActDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        DeleteAct deleteAct = te.getAnnotation(DeleteAct.class);

        ((App) App.APP).deleteActName = deleteAct.name();
        ((App) App.APP).deleteActPackages = deleteAct.packages();

    }
}
