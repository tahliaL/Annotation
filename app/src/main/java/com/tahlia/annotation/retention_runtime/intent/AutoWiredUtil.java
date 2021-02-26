package com.tahlia.annotation.retention_runtime.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.tahlia.annotation.AutoWired;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AutoWiredUtil {

    public static void bind(Activity activity) {
        Bundle bundle = activity.getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        Class<? extends Activity> activityClass = activity.getClass();
        Field[] declaredFields = activityClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(AutoWired.class)) {
                AutoWired autoWiredAnnotation = field.getAnnotation(AutoWired.class);
                String intentKey = autoWiredAnnotation.value();
                intentKey = intentKey.isEmpty() ? field.getName() : intentKey;
                if (bundle.containsKey(intentKey)) {
                    Object o = bundle.get(intentKey);

                    // 获取数组中单个元素类型（clz），如果不是数组的话，会返回null
                    Class<?> componentType = field.getType().getComponentType();
                    // 说明类型是Parceable[] 类型
                    if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)) {
                        // 既然是数组类型，就可以强转成数组，但是此时类型是Object的
                        Object[] obj = (Object[]) o;
                        // 转成对应类型的数组
                        Object[] objects = Arrays.copyOf(obj, obj.length, (Class<? extends Object[]>) field.getType());
                        o = objects;
                    }

                    field.setAccessible(true);
                    try {
                        field.set(activity, o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}
