package core.tools;

import android.content.Intent;

public class PassForApplib {
    public static final String FROM_ACT = "fromAct";

    public static final String fromAct(Intent data) {
        return data.getStringExtra(FROM_ACT);
    }


}

