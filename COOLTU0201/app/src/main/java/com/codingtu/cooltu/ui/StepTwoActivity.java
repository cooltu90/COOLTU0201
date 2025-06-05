package com.codingtu.cooltu.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.view.radiogroup.RadioGroup;
import com.codingtu.cooltu.lib4a.view.radiogroup.RadioGroup0;
import com.codingtu.cooltu.lib4a.view.radiogroup.RadioGroup1;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.StepTwoActivityBase;
import core.actres.StepTwoActivityRes;

@To(StepTwoActivityRes.class)
@ActBase(layout = R.layout.activity_step_two)
public class StepTwoActivity extends StepTwoActivityBase {

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        RadioGroup.<Integer>viewGroup(this, ll)
                .setOnSetItem(new RadioGroup.OnSetItem() {
                    @Override
                    public void setSelected(View view) {
                        ((TextView) view).setTextColor(0xff00ff00);
                    }

                    @Override
                    public void setSelectno(View view) {
                        ((TextView) view).setTextColor(0xff000000);
                    }
                })
                .addOnSelectChange(new RadioGroup.OnSelectChange<RadioGroup0<Integer>>() {
                    @Override
                    public void onChange(RadioGroup0<Integer> rg, int selected) {
                        Integer currentItem = rg.getCurrentItem();
                        Logs.i(currentItem);
                    }
                }).setItems(1, 2, 3, 4).setSelected(3);


        RadioGroup.<Integer>xxx(this, ll1)
                .setItems(8, 9, 10, 11)
                .createItemViews(new RadioGroup.CreateItemViews<Integer>() {
                    @Override
                    public View createItemView(Context context, Integer item) {
                        TextView textView = new TextView(context);
                        textView.setText(item + "");
                        return textView;
                    }
                }).setOnSetItem(new RadioGroup.OnSetItem() {
                    @Override
                    public void setSelected(View view) {
                        Logs.i(view);
                        ((TextView) view).setTextColor(0xff00ff00);
                    }

                    @Override
                    public void setSelectno(View view) {
                        ((TextView) view).setTextColor(0xff000000);
                    }
                }).addOnSelectChange(new RadioGroup.OnSelectChange<RadioGroup1<Integer>>() {
                    @Override
                    public void onChange(RadioGroup1<Integer> rg, int selected) {
                        Integer currentItem = rg.getCurrentItem();
                        Logs.i(currentItem);
                    }
                }).setSelected(1);


    }
}

