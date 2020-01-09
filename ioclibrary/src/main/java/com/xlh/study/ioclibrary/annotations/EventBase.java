package com.xlh.study.ioclibrary.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by L470 on 2019/12/25.
 */

@Target(ElementType.ANNOTATION_TYPE) // 该注解作用在另一个注解上
@Retention(RetentionPolicy.RUNTIME) // jvm在运行时通过反射技术，获取注解的值
public @interface EventBase {

    // 点击事件3个相同点
    // 1.监听的方法名：setXxxListener()
    String listenerSetter();

    // 2.监听的对象：new View.OnClickListener
    Class<?> listenerType();

    // 3.回调方法
    String callBackListener();
}
