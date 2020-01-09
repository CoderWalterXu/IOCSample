package com.xlh.study.ioclibrary.annotations;


import com.xlh.study.ioclibrary.recyclerview.RVClick;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by L470 on 2019/12/25.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setOnItemClickListener",
        listenerType = RVClick.OnItemClickListener.class,
        callBackListener = "onItemClick")
public @interface OnItemClick {
    // int数组形式，多id多控件共用某点击方法({R.id.tv,R.id.btn})
    int[] value();
}
