package com.xlh.study.ioclibrary.annotations;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Watler Xu
 * time:2020/1/9
 * description:
 * version:0.0.1
 */
@Target(ElementType.METHOD)// 作用于方法
@Retention(RetentionPolicy.RUNTIME)// 需要通过反射获取注解的值
@EventBase(listenerSetter = "setOnLongClickListener",
        listenerType = View.OnLongClickListener.class,
        callBackListener = "onLongClick")
public @interface OnLongClick {
    // int数组形式，多id多控件共用某点击方法
    int[] value();
}