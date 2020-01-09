package com.xlh.study.ioclibrary.annotations;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by L470 on 2019/12/25.
 */

@Target(ElementType.METHOD) // 作用在方法上
@Retention(RetentionPolicy.RUNTIME) // jvm在运行时通过反射技术，获取注解的值
@EventBase(listenerSetter = "setOnClickListener",
        listenerType = View.OnClickListener.class,
        callBackListener = "onClick")
public @interface OnClick {
    // int数组形式，多id多控件共用某点击方法({R.id.tv,R.id.btn})
    int[] value();
}
