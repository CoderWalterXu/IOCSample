package com.xlh.study.ioclibrary;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by L470 on 2019/12/25.
 */

public class ListenerInvovationHandler implements InvocationHandler {
    /**
     * 需要拦截的对象，比如在Activity中本应该执行的onClick方法
     */
    private Object target;
    /**
     * 需要拦截的方法Map集合
     * key是onClick。value是开发者自己定义的方法，比如click(View view)
     */
    private HashMap<String, Method> methodHashMap = new HashMap<>();

    public ListenerInvovationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (target != null) {
            // 获取需要拦截的方法名
            String name = method.getName(); // 假如是onClick方法
            // 重新赋值，将拦截的方法替换为自定义的方法
            method = methodHashMap.get(name);
            if (method != null) {
                if(method.getGenericParameterTypes().length==0){
                    return method.invoke(target);
                }
                // 确实找到了需要拦截，才执行自定义方法
                return method.invoke(target, args);
            }
        }
        return null;
    }

    /**
     * 将需要拦截的方法加入Map集合
     * 拦截的方法，和替换执行的方法
     * @param methodName 需要拦截的方法：本应该执行的onClick回调方法
     * @param method 执行拦截后的方法：比如click()
     */
    public void addMethod(String methodName, Method method){
        methodHashMap.put(methodName,method);
    }

}
