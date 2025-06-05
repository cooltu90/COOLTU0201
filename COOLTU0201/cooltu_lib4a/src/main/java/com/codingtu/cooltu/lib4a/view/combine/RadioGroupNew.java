package com.codingtu.cooltu.lib4a.view.combine;

import android.view.View;
import android.view.ViewGroup;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.CountTool;

public class RadioGroupNew<T> implements View.OnClickListener, OnDestroy {
    private BaseEs<View> itemViewEs;
    private OnSetItem onSetItem;
    private OnClick onClick;
    private BaseEs<OnSelectChange> onSelectChanges;
    private Integer selected;
    private boolean hasNull;
    private BaseEs<T> itemEs;

    private RadioGroupNew(Destroys destroys) {
        destroys.add(this);
    }

    public static RadioGroupNew obtain(Destroys destroys) {
        return new RadioGroupNew(destroys);
    }

    public static interface OnSetItem {

        public void setSelected(View view);

        public void setSelectno(View view);

    }

    public static interface OnClick {
        public boolean onClick(int index, View view);
    }

    public static interface OnSelectChange {
        public void onChange(int selected);
    }

    public RadioGroupNew setItemViews(ViewGroup vp) {
        if (vp.getChildCount() > 0) {
            this.itemViewEs = Es.es(new Es.EachGetter<View>() {
                @Override
                public int count() {
                    return vp.getChildCount();
                }

                @Override
                public View get(int i) {
                    View childAt = vp.getChildAt(i);
                    childAt.setTag(R.id.tag_0, i);
                    childAt.setOnClickListener(RadioGroupNew.this);
                    return childAt;
                }
            });
        }
        return this;
    }

    public RadioGroupNew setBts(View... itemViews) {
        this.itemViewEs = Es.es(itemViews);
        this.itemViewEs.ls(new Es.EachEs<View>() {
            @Override
            public boolean each(int position, View view) {
                view.setTag(R.id.tag_0, position);
                view.setOnClickListener(RadioGroupNew.this);
                return false;
            }
        });
        return this;
    }

    public RadioGroupNew setOnSetItem(OnSetItem onSetItem) {
        this.onSetItem = onSetItem;
        return this;
    }

    public RadioGroupNew setOnClick(OnClick onClick) {
        this.onClick = onClick;
        return this;
    }

    public RadioGroupNew addOnSelectChange(OnSelectChange onSelectChange) {
        if (onSelectChanges == null) {
            onSelectChanges = Es.es();
        }
        onSelectChanges.add(onSelectChange);
        return this;
    }

    public RadioGroupNew setHasNull(boolean hasNull) {
        this.hasNull = hasNull;
        return this;
    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag(R.id.tag_0);
        if (onClick != null) {
            if (!onClick.onClick(index, v)) {
                return;
            }
        }
        setSelected(index);
    }

    public RadioGroupNew setSelected(int index) {
        if (selected == null) {
            setSelectedReal(index);
        } else if (index == selected) {
            if (hasNull) {
                setSelectedReal(-1);
            }
        } else {
            setSelectedReal(index);
        }
        return this;
    }

    private void setSelectedReal(int index) {
        this.selected = index;
        change();
        for (int i = 0; i < CountTool.count(onSelectChanges); i++) {
            OnSelectChange onSelectChange = onSelectChanges.getByIndex(i);
            if (onSelectChange != null)
                onSelectChange.onChange(this.selected);
        }
    }

    private void change() {
        if (itemViewEs != null) {
            itemViewEs.ls(new Es.EachEs<View>() {
                @Override
                public boolean each(int i, View view) {
                    if (selected == i) {
                        if (onSetItem != null) {
                            onSetItem.setSelected(view);
                        }
                    } else {
                        if (onSetItem != null) {
                            onSetItem.setSelectno(view);
                        }
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void destroy() {
        itemViewEs.clear();
        itemViewEs = null;
        onSetItem = null;
    }
}
