package com.codingtu.cooltu.lib4a.view.radiogroup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.codingtu.cooltu.lib4j.destory.Destroys;

public class RadioGroup {

    ///////////////////////////////////////////////////////
    //
    //
    //
    ///////////////////////////////////////////////////////
    public static interface OnSetItem {

        public void setSelected(View view);

        public void setSelectno(View view);

    }

    public static interface OnClick {
        public boolean onClick(int index, View view);
    }

    public static interface OnSelectChange<E extends RadioGroupBase> {
        public void onChange(E rg, int selected);
    }

    public static interface CreateItemViews<E> {
        public View createItemView(Context context, E e);
    }

    ///////////////////////////////////////////////////////
    //
    //
    //
    ///////////////////////////////////////////////////////
    public static <E> RadioGroup0<E> setViewItems(Destroys destroys, ViewGroup vp) {
        RadioGroup0<E> radioGroup = new RadioGroup0<>(vp);
        destroys.add(radioGroup);
        return radioGroup;
    }

    public static <E> RadioGroup0<E> itemViews(Destroys destroys, View... itemViews) {
        RadioGroup0<E> radioGroup = new RadioGroup0<>(itemViews);
        destroys.add(radioGroup);
        return radioGroup;
    }

    public static <E> RadioGroup1<E> createViewItems(Destroys destroys, ViewGroup vp) {
        RadioGroup1<E> radioGroup = new RadioGroup1<>(vp);
        destroys.add(radioGroup);
        return radioGroup;
    }


}
