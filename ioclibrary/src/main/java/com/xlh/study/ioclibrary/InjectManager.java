package com.xlh.study.ioclibrary;

import android.app.Activity;
import android.view.View;


import com.xlh.study.ioclibrary.annotations.ContentView;
import com.xlh.study.ioclibrary.annotations.EventBase;
import com.xlh.study.ioclibrary.annotations.InjectView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by L470 on 2019/12/25.
 * 注解管理类
 */
public class InjectManager {
    public static void inject(Activity activity) {
        // 布局的注入
        injectLayout(activity);
        // 控件的注入
        injectViews(activity);
        // 事件的注入
        injectEvents(activity);
    }

    /**
     * 布局的注入
     * 思路：获得类--->布局注解--->注解的值--->获取指定方法--->执行方法
     *
     * @param activity
     */
    private static void injectLayout(Activity activity) {
        // 获取类
        Class<? extends Activity> clazz = activity.getClass();
        // 获取类上的注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        // 判断是否null，因为并不是所有传入的类都有注解
        if (contentView != null) {
            // 获取ContentView注解的值(如R.layout.activity_main的int值)
            int layoutId = contentView.value();
            // 第一种写法，反射获取setContentView方法，并执行
            /*try {
                // 获取setConetentView方法，因为该方法在父类中，使用getMethod而不是getDelaredMethod
                Method method = clazz.getMethod("setContentView", int.class);
                // 执行方法
                method.invoke(activity, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            // 第二种写法，通过activity对象直接调用setContentView方法
            activity.setContentView(layoutId);
        }
    }

    /**
     * 控件的注入
     * 思路：获得类--->获取所有属性，包含私有--->遍历属性--->每个属性的注解值--->获得指定方法--->执行方法--->将私有属性设置可以访问--->赋值
     *
     * @param activity
     */
    private static void injectViews(Activity activity) {
        // 获取类
        Class<? extends Activity> clazz = activity.getClass();
        // getDeclaredFields获取该类的所有属性，包括私有属性
        Field[] fields = clazz.getDeclaredFields();
        // 循环遍历，拿到每个属性并处理
        for (Field field : fields) {
            // 获得属性上的注解
            InjectView injectView = field.getAnnotation(InjectView.class);
            // 判断是否null，因为并不是所有属性都有注解
            if (injectView != null) {
                // 获取注解值(如R.id.tv的int值)
                int viewId = injectView.value();
                // 第一种写法，反射获取findViewById方法，并执行
                /*try {
                    // 获取findViewById方法
                    Method method = clazz.getMethod("findViewById", int.class);
                    // 获得view对象，用于赋值
                    Object view = method.invoke(activity, viewId);
                    // 将私有属性设置可以访问
                    field.setAccessible(true);
                    // 在当前Activity,将属性的值赋给控件
                    field.set(activity, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                // 第二种写法,通过activity对象直接调用findViewById方法
                try {
                    Object view = activity.findViewById(viewId);
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 事件的注入
     *
     * @param activity
     */
    public static void injectEvents(Activity activity) {
        // 获取类
        Class<? extends Activity> clazz = activity.getClass();
        // 获取类的所有方法，包含私有
        Method[] methods = clazz.getDeclaredMethods();
        // 循环遍历，拿到每个方法并处理
        for (Method method : methods) {
            // 方法上可能有多个注解
            Annotation[] annotations = method.getAnnotations();
            // 循环遍历找到方法上的每个注解
            for (Annotation annotation : annotations) {
                // 获取注解上的注解
                // 获取OnClick注解上的注解类型
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    // 通过EventBase指定获取
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                    // 判断是否null，因为有些方法没有EventBase注解
                    if (eventBase != null) {
                        // 事件的3个重要信息
                        String listenerSetter = eventBase.listenerSetter();
                        Class<?> listenerType = eventBase.listenerType();
                        String callBackListener = eventBase.callBackListener();
                        // 获取注解的值，执行方法再去获得注解的值
                        try {
                            // 通过annotationType获取onClick注解的value值
                            Method valueMethod = annotationType.getDeclaredMethod("value");
                            // 执行value方法，获取@OnClick注解的值
                            int[] viewIds = (int[]) valueMethod.invoke(annotation);
                            // 代理方式
                            // 拦截方法
                            // 得到监听的代理对象（新建代理单例、类的加载器、指定要代理的对象类的类型、class实例）
                            ListenerInvovationHandler handler = new ListenerInvovationHandler(activity);
                            // 添加到拦截列表里面
                            handler.addMethod(callBackListener, method);
                            // 代理的方式，外面是什么注解，就匹配对应的监听方法和回调方法
                            // 监听对象的代理对象
                            //ClassLoader：指定当前目标对象使用类加载器，获取加载器的方法是固定的
                            //Class<?>[] interfaces:目标对象实现的接口的类型，使用泛型方式确认类型
                            //InvocationHandler：事件处理，执行目标对象的方法时，会触发事件处理器的方法
                            Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);
                            //btn控件没有赋值，所以不能btn.setOnClickListener()
                            //遍历注解的值
                            for (int viewId : viewIds) {
                                // 获得当前activity的view
                                View view = activity.findViewById(viewId);
                                // 获取指定的方法
                                Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                                // 执行方法
                                setter.invoke(view, listener);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

}
