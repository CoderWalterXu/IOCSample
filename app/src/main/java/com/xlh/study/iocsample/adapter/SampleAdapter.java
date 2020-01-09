package com.xlh.study.iocsample.adapter;

import android.widget.TextView;

import com.xlh.study.ioclibrary.recyclerview.RVClickAdapter;
import com.xlh.study.ioclibrary.recyclerview.RVClickHolder;
import com.xlh.study.iocsample.R;
import com.xlh.study.iocsample.bean.ItemBean;

import java.util.List;

/**
 * @author: Watler Xu
 * time:2020/1/9
 * description:
 * version:0.0.1
 */
public class SampleAdapter extends RVClickAdapter<ItemBean> {


    public SampleAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_rv;
    }

    @Override
    public void convert(RVClickHolder holder, ItemBean itemBean) {
        TextView textView = holder.getView(R.id.item_tv);
        textView.setText(itemBean.toString());
    }
}
