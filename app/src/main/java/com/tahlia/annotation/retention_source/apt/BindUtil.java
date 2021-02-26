package com.tahlia.annotation.retention_source.apt;

import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindUtil {
    public static void bind(Activity activity) {
        String viewBindName = activity.getClass().getName()+"_ViewBinder";
        try {
            Class<?> viewBindClz = Class.forName(viewBindName);
            Method method = viewBindClz.getMethod("bind", activity.getClass());
            method.invoke(null, activity);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
