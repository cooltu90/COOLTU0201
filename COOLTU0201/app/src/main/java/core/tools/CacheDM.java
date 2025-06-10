package core.tools;

import com.codingtu.cooltu.lib4a.dm.BaseCacheDM;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class CacheDM {


    public static void cacheName(java.lang.String name) {
        BaseCacheDM.cache("name", name);
    }

    public static java.lang.String getName() {
        return BaseCacheDM.getCache(java.lang.String.class, "name");
    }

    public static void cacheAge(int age) {
        BaseCacheDM.cache("age", age);
    }

    public static int getAge() {
        return BaseCacheDM.getCache(int.class, "age");
    }

}
