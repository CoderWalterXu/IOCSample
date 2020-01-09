package com.xlh.study.iocsample.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xlh.study.ioclibrary.InjectManager;
import com.xlh.study.ioclibrary.annotations.ContentView;
import com.xlh.study.ioclibrary.annotations.InjectView;
import com.xlh.study.ioclibrary.annotations.OnItemClick;
import com.xlh.study.ioclibrary.annotations.OnItemLongClick;
import com.xlh.study.ioclibrary.recyclerview.RVClick;
import com.xlh.study.iocsample.R;
import com.xlh.study.iocsample.adapter.SampleAdapter;
import com.xlh.study.iocsample.base.BaseIOCActivity;
import com.xlh.study.iocsample.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author: Watler Xu
 * time:2020/1/9
 * description:
 * version:0.0.1
 */
@ContentView(R.layout.activity_recyclerview)
public class RVClickActivity extends BaseIOCActivity {


    @InjectView(R.id.recyclerView)
    private RVClick recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRV();
    }

    private List<ItemBean> initData() {
        List<ItemBean> data = new ArrayList<>();
        for (int i = 0; i < 49; i++) {
            data.add(new ItemBean(i + "", "watler"));
        }
        return data;
    }

    private void initRV() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        SampleAdapter adapter = new SampleAdapter(initData());
        recyclerView.setRVClickAdapter(adapter);
        recyclerView.setAdapter(adapter);

        // BaseIOCActivity中调用InjectManager.inject(this);前面没有初始化好adapter
        // 所以需要在创建adapter后，再调用一次
        InjectManager.injectEvents(this);
    }

    @OnItemClick(R.id.recyclerView)
    public void itemClick(View view, ItemBean itemBean, int position) {
        Toast.makeText(this, "点击了条目" + itemBean.getIndex(), Toast.LENGTH_SHORT).show();
    }

    @OnItemLongClick(R.id.recyclerView)
    public boolean itemLongClick(View view, ItemBean itemBean, int position) {
        Toast.makeText(this, "长按了条目" + itemBean.getIndex(), Toast.LENGTH_SHORT).show();
        return true;
    }

}
