package com.tahlia.annotation.poet;

import android.util.Log;

import com.tahlia.annotation.CountTime;

import androidx.annotation.NonNull;

public class CountTimeClz {
    private static final String TAG = "CountTimeClz";
    @CountTime
    public void method1(){
        Log.d(TAG, "method1: ");
    }

    protected void method2() {
        Log.d(TAG, "method2: ");
    }
    
    private void method3() {
        Log.d(TAG, "method3: ");
    }

    @NonNull
    @Override
    @CountTime
    public String toString() {
        Log.d(TAG, "toString: ");
        return super.toString();
    }
}
