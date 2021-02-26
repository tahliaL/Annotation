package com.tahlia.annotation.retention_runtime.bind;

import android.app.Activity;
import android.view.View;

import com.tahlia.annotation.BindView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindUtil {

    public static void bind(Activity activity) {
        // 反射是基于class的，class可以获取到该类声明的所有方法、变量，甚至拿到父类声明的方法、变量。
        Class<? extends Activity> activityClz = activity.getClass();
        // 通过clz.getDeclaredFields获取到声明的所有变量，包括私有的
        Field[] declaredFields = activityClz.getDeclaredFields();
        for (Field field : declaredFields) {
            // 找到被BindView注解修饰的变量
            if (field.isAnnotationPresent(BindView.class)) {
                // 获取BindView注解中传入的值，即id
                BindView bindViewAnnotation = field.getAnnotation(BindView.class);
                int idValue = bindViewAnnotation.value();
                // 通过activity对象调用findViewById，得到与id绑定的View
                View view = activity.findViewById(idValue);
                // 强制赋予可入权限，否则private类型的无法反射赋值成功
                field.setAccessible(true);
                try {
                    // 反射赋值，将绑定的view赋值给传入的activity对象被注解修饰的目标变量
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
