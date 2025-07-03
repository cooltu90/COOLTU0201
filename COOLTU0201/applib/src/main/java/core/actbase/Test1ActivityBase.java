package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class Test1ActivityBase extends com.codingtu.cooltu.lib4a.ui.act.CoreActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{

    public String baseClassName = "Test1ActivityBase";

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());








        String nowBaseClassName = getClass().getSimpleName() + "Base";
        if (nowBaseClassName.equals(baseClassName)) {
            onCreateComplete();
        }
    }

    protected int getLayout() {
        return -1;
    }


    @Override
    public void onCreateComplete() {
        super.onCreateComplete();






    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {

            }
        } catch (Exception e) {
            com.codingtu.cooltu.lib4a.log.Logs.e(e);
            if (!(e instanceof com.codingtu.cooltu.lib4a.exception.NotToastException)) {
                toast(e.getMessage());
            }
        }
    }



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

    }










}

