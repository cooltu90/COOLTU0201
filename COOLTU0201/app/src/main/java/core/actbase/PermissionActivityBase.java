package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class PermissionActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.LinearLayout installLl;
    protected android.widget.TextView locationTv;
    protected android.widget.TextView cameraTv;
    protected android.widget.TextView storeTv;
    protected android.widget.TextView bluetoothTv;
    protected android.widget.TextView installBt;
    protected android.widget.LinearLayout version11Ll;
    protected android.widget.LinearLayout bluetoothLl;
    protected android.widget.TextView version11Bt;

    public String baseClassName = "PermissionActivityBase";

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        installLl = findViewById(com.codingtu.cooltu.R.id.installLl);
        locationTv = findViewById(com.codingtu.cooltu.R.id.locationTv);
        cameraTv = findViewById(com.codingtu.cooltu.R.id.cameraTv);
        storeTv = findViewById(com.codingtu.cooltu.R.id.storeTv);
        bluetoothTv = findViewById(com.codingtu.cooltu.R.id.bluetoothTv);
        installBt = findViewById(com.codingtu.cooltu.R.id.installBt);
        version11Ll = findViewById(com.codingtu.cooltu.R.id.version11Ll);
        bluetoothLl = findViewById(com.codingtu.cooltu.R.id.bluetoothLl);
        version11Bt = findViewById(com.codingtu.cooltu.R.id.version11Bt);







        String nowBaseClassName = getClass().getSimpleName() + "Base";
        if (nowBaseClassName.equals(baseClassName)) {
            onCreateComplete();
        }
    }

    protected int getLayout() {
        return com.codingtu.cooltu.R.layout.activity_permission;
    }


    @Override
    public void onCreateComplete() {
        super.onCreateComplete();


        version11Bt.setOnClickListener(this);
        storeTv.setOnClickListener(this);
        cameraTv.setOnClickListener(this);
        locationTv.setOnClickListener(this);
        bluetoothTv.setOnClickListener(this);
        installBt.setOnClickListener(this);




    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {
                case com.codingtu.cooltu.R.id.version11Bt:
                    version11BtClick(
                    );
                    break;
                case com.codingtu.cooltu.R.id.storeTv:
                case com.codingtu.cooltu.R.id.cameraTv:
                case com.codingtu.cooltu.R.id.locationTv:
                case com.codingtu.cooltu.R.id.bluetoothTv:
                    permisstionBtClick(
                    );
                    break;
                case com.codingtu.cooltu.R.id.installBt:
                    installBtClick(
                    );
                    break;

            }
        } catch (Exception e) {
            com.codingtu.cooltu.lib4a.log.Logs.e(e);
            if (!(e instanceof com.codingtu.cooltu.lib4a.exception.NotToastException)) {
                toast(e.getMessage());
            }
        }
    }

    protected void version11BtClick() throws Exception {}
    protected void permisstionBtClick() throws Exception {}
    protected void installBtClick() throws Exception {}


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {

        }

        return false;

    }


    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {

        }
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {
        super.back(requestCode, permissions, grantResults);
        if (requestCode == core.tools.Permissions.CODE_CHECK_IN_PERMISSION_ACTIVITY) {
            check();
        }

    }
    protected void check() {}










}

