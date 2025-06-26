package com.codingtu.cooltu.dm;

import com.codingtu.cooltu.processor.annotation.dm.Cache;
import com.codingtu.cooltu.processor.annotation.dm.CacheConfig;

@CacheConfig("NowCreateDocDM")
public class NowCreateDocDM1 {

    @Cache
    String name;

    @Cache
    int age;

}
