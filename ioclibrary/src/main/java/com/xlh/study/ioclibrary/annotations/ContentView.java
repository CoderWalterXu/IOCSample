package com.xlh.study.ioclibrary.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by L470 on 2019/12/25.
 */

@Target(ElementType.TYPE) // type作用在类上
@Retention(RetentionPolicy.RUNTIME) //jvm在运行时通过反射技术，获取注解的值
public @interface ContentView {
    // 返回int类型的布局值(比如R.layout.activity_main)
    int value();
}
