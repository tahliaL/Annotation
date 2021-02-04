package com.tahlia.annotation.annotationUtil;

import android.app.Activity;
import android.view.View;

import com.tahlia.annotation.annotation.BindViewReflect;

import java.lang.reflect.Field;

public class BindUtils {
    public static void Bind(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(BindViewReflect.class)) {
                BindViewReflect annotation = field.getAnnotation(BindViewReflect.class);
                int id = annotation.value();
                View viewById = activity.findViewById(id);
                field.setAccessible(true);
                try {
                    field.set(activity, viewById);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
