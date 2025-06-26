package core.cache;

import com.codingtu.cooltu.lib4a.dm.BaseCacheDM;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class NowCreateDocDM {


    public static void name(java.lang.String name) {
        BaseCacheDM.cache("NowCreateDocDM_name", name);
    }

    public static java.lang.String name() {
        return BaseCacheDM.getCache(java.lang.String.class, "NowCreateDocDM_name");
    }

    public static void age(int age) {
        BaseCacheDM.cache("NowCreateDocDM_age", age);
    }

    public static int age() {
        return BaseCacheDM.getCache(int.class, "NowCreateDocDM_age");
    }

}
