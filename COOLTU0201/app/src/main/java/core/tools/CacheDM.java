package core.tools;

import com.codingtu.cooltu.lib4a.dm.BaseCacheDM;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class CacheDM {


    public static void cacheName(Destroys destroys, java.lang.String name) {
        BaseCacheDM.cache(destroys, "name", name);
    }

    public static java.lang.String getName() {
        return BaseCacheDM.getCache("name");
    }

}
