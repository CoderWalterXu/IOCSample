package com.xlh.study.ioclibrary.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by L470 on 2019/12/25.
 */
@Target(ElementType.FIELD) // 作用在属性
@Retention(RetentionPolicy.RUNTIME) // jvm在运行时通过反射技术，获取注解的值
public @interface InjectView {
    // 返回int类型的控件id值（R.id.tv）
    int value();
}
