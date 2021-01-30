package com.tahlia.annotation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tahlia.annotation.annotationUtil.ExtraUtils;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ExtraUtils.parse(this);
    }
}
