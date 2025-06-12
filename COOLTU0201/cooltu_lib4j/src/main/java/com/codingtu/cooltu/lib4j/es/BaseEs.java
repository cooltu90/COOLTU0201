package com.codingtu.cooltu.lib4j.es;

import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tools.OtherTool;

import java.util.List;

public class BaseEs<E> extends CoreEs<E, BaseEs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public BaseEs() {
    }

    public BaseEs(List<E> list) {
        super(list);
    }
}
