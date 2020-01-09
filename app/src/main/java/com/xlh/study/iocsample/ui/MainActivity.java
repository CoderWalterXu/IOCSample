package com.xlh.study.iocsample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xlh.study.ioclibrary.annotations.ContentView;
import com.xlh.study.ioclibrary.annotations.InjectView;
import com.xlh.study.ioclibrary.annotations.OnClick;
import com.xlh.study.ioclibrary.annotations.OnLongClick;
import com.xlh.study.iocsample.R;
import com.xlh.study.iocsample.base.BaseIOCActivity;

// 注入布局
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseIOCActivity {

    // 可以被private修饰
    @InjectView(R.id.tv)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注意，使用了IOC注入后，不能有setContentView()，否则IOC相关的都无效

        // 传统findViewByid，被@InjectView替代
        // TextView tv = findViewById(R.id.tv);

        // 验证@InjectView是否可用
        tv.setText("Hello IOC!");

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 验证@InjectView是否可用
        Toast.makeText(this, tv.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.tv, R.id.btn, R.id.btn_rv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv:
                Toast.makeText(this, "点击了tv", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn:
                Toast.makeText(this, "点击了Button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_rv:
                startActivity(new Intent(this, RVClickActivity.class));
                break;
            default:
                break;
        }
    }

    @OnLongClick({R.id.tv, R.id.btn, R.id.btn_rv})
    public boolean longClick(View btn) {
        switch (btn.getId()) {
            case R.id.tv:
                Toast.makeText(this, "长按了tv", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn:
                Toast.makeText(this, "长按了btn", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }


}
