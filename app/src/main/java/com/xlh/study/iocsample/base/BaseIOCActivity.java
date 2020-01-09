package com.xlh.study.iocsample.base;

import android.os.Bundle;

import com.xlh.study.ioclibrary.InjectManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author: Watler Xu
 * time:2020/1/9
 * description:
 * version:0.0.1
 */
public class BaseIOCActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 帮助子类进行布局、控件、事件的注入
        InjectManager.inject(this);
    }
}