package com.tahlia.annotation.annotationUtil;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.tahlia.annotation.annotation.IntentExtra;

import java.lang.reflect.Field;

public class ExtraUtils {
    private static final String TAG = "ExtraUtils";

    public static void parse(Activity activity) {
        Intent intent = activity.getIntent();
        if (intent == null) {
            Log.e(TAG, "parse "+activity.getClass().getName()+": Intent = null!");
            return;
        }

        Class<? extends Activity> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(IntentExtra.class)){
                IntentExtra annotation = field.getAnnotation(IntentExtra.class);
                String value = annotation.value();
                if (value.isEmpty()) {
                    value = field.getName();
                }

                Object o = intent.getExtras().get(value);
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
