package com.codingtu.cooltu.lib4a.view.radiogroup;

import android.view.View;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.CountTool;

import java.util.List;

public class RadioGroupBase<E, THIS extends RadioGroupBase> implements View.OnClickListener, OnDestroy {

    protected BaseEs<View> itemViewEs;
    protected RadioGroup.OnSetItem onSetItem;
    protected RadioGroup.OnClick onClick;
    protected BaseEs<RadioGroup.OnSelectChange> onSelectChanges;
    protected boolean hasNull;
    protected Integer selected;
    protected BaseEs<E> itemEs;

    @Override
    public void destroy() {

    }

    public THIS setOnSetItem(RadioGroup.OnSetItem onSetItem) {
        this.onSetItem = onSetItem;
        return (THIS) this;
    }


    public THIS setOnClick(RadioGroup.OnClick onClick) {
        this.onClick = onClick;
        return (THIS) this;
    }

    public THIS addOnSelectChange(RadioGroup.OnSelectChange<THIS> onSelectChange) {
        if (onSelectChanges == null) {
            onSelectChanges = Es.es();
        }
        onSelectChanges.add(onSelectChange);
        return (THIS) this;
    }

    public THIS setHasNullBase(boolean hasNull) {
        this.hasNull = hasNull;
        return (THIS) this;
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


    public THIS setSelected(int index) {
        if (selected == null) {
            setSelectedReal(index);
        } else if (index == selected) {
            if (hasNull) {
                setSelectedReal(-1);
            }
        } else {
            setSelectedReal(index);
        }
        return (THIS) this;
    }

    private void setSelectedReal(int index) {
        this.selected = index;
        change();
        for (int i = 0; i < CountTool.count(onSelectChanges); i++) {
            RadioGroup.OnSelectChange onSelectChange = onSelectChanges.getByIndex(i);
            if (onSelectChange != null)
                onSelectChange.onChange(this, this.selected);
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

    public E getCurrentItem() {
        if (selected == null)
            return null;
        return itemEs.getByIndex(selected);
    }


    public THIS setItems(BaseEs<E> items) {
        this.itemEs = items;
        return (THIS) this;
    }

    public THIS setItems(E... items) {
        return setItems(Es.es(items));
    }

    public THIS setItems(List<E> items) {
        return setItems(Es.es(items));
    }

}
