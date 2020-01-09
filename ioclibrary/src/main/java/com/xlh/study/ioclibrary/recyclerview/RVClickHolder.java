package com.xlh.study.ioclibrary.recyclerview;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: Watler Xu
 * time:2020/1/9
 * description:
 * version:0.0.1
 */
public class RVClickHolder extends RecyclerView.ViewHolder{

    private SparseArray<View> mViews; // View控件集合
    private View mConvertView; // 当前View

    public RVClickHolder(@NonNull View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    /**
     * 创建RecycleViewViewHold对象
     *
     * @param context  上下文
     * @param parent   父布局
     * @param layoutId 布局ID
     * @return RViewHolder
     */
    static RVClickHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RVClickHolder(itemView);
    }

    /**
     * 通过viewId获取控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    View getConvertView() {
        return mConvertView;
    }

}
