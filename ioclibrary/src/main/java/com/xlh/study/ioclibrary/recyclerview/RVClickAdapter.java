package com.xlh.study.ioclibrary.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: Watler Xu
 * time:2020/1/9
 * description:
 * version:0.0.1
 */
public abstract class RVClickAdapter <T> extends RecyclerView.Adapter<RVClickHolder> {

    // 条目点击事件监听
    private RVClick.OnItemClickListener<T> mOnItemClickListener;
    // 条目长按事件监听
    private RVClick.OnItemLongClickListener<T> mOnItemLongClickListener;
    private List<T> data;

    public RVClickAdapter(List<T> data) {
        if (data == null){
            this.data = new ArrayList<>();
        }
        this.data = data;
    }

    public void setOnItemClickListener(RVClick.OnItemClickListener<T> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(RVClick.OnItemLongClickListener<T> onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public abstract int getLayoutId();

    public abstract void convert(RVClickHolder holder, T t);

    @NonNull
    @Override
    public RVClickHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutId = getLayoutId();
        RVClickHolder holder = RVClickHolder.createViewHolder(viewGroup.getContext(), viewGroup, layoutId);
        setListener(holder);
        return holder;
    }

    private void setListener(final RVClickHolder holder) {
        if (holder == null){
            return;
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != -1) { // 防止连续点击同一条目而且是删除操作
                        mOnItemClickListener.onItemClick(v, data.get(position), position);
                    }
                }
            }
        });

        holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    int position = holder.getAdapterPosition();
                    return mOnItemLongClickListener.onItemLongClick(v, data.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull RVClickHolder holder, int position) {
        convert(holder, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
