package com.xlh.study.ioclibrary.recyclerview;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: Watler Xu
 * time:2020/1/9
 * description:
 * version:0.0.1
 */
public class RVClick extends RecyclerView {

    private RVClickAdapter adapter;

    public RVClick(@NonNull Context context) {
        super(context);
    }

    public RVClick(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RVClick(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRVClickAdapter(RVClickAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * 条目点击监听接口
     */
    public interface OnItemClickListener<T> {

        /**
         * 点击事件监听
         */
        void onItemClick(View view, T entity, int position);
    }

    /**
     * 条目长按监听接口
     */
    public interface OnItemLongClickListener<T> {

        /**
         * 长按事件监听
         */
        boolean onItemLongClick(View view, T entity, int position);
    }

    /**
     * 设置点击监听属性
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (adapter != null) {
            adapter.setOnItemClickListener(onItemClickListener);
        }
    }

    /**
     * 设置长按监听属性
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        if (adapter != null) {
            adapter.setOnItemLongClickListener(onItemLongClickListener);
        }
    }

}
