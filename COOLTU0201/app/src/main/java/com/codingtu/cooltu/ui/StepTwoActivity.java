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
    }
}

