package core.fragmentbase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class Test2FragmentBase extends com.codingtu.cooltu.lib4a.ui.fragment.CoreFragment implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{

    public String baseClassName = "Test2FragmentBase";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = com.codingtu.cooltu.lib4a.tools.InflateTool.inflate(inflater, getLayout(), container);
        String nowBaseClassName = getClass().getSimpleName() + "Base";
        if (nowBaseClassName.equals(baseClassName)) {
            onCreateComplete();
        }
        return view;
    }


    protected int getLayout() {
        return -1;
    }



    @Override
    public void onCreateComplete() {
        super.onCreateComplete();






    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

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








}
