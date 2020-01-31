package com.muzi.weshop.common;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * @author muzi
 */
public class DiffTCallback<T> extends DiffUtil.Callback {
    
    private List<T> oldData , newData;


    public DiffTCallback(List<T> oldData, List<T> newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    @Override
    public int getOldListSize() {
        return oldData != null ? oldData.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newData != null ? newData.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        T oldT = oldData.get(oldItemPosition);
        T newT = newData.get(newItemPosition);
        if (!oldT.equals(newT)) {
            //如果有内容不同，就返回false
            return false;
        }
        if (oldT != newT) {
            //如果有内容不同，就返回false
            return false;
        }
        //默认两个data内容是相同的
        return true; 

    }
}
