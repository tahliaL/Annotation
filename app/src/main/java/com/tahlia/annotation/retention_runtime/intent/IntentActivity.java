package com.tahlia.annotation.retention_runtime.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.tahlia.annotation.AutoWired;
import com.tahlia.annotation.R;

import java.util.Arrays;
import java.util.List;

public class IntentActivity extends AppCompatActivity {

    int num;

    @AutoWired
    String name;
    @AutoWired
    boolean isMale;
    @AutoWired("arr")
    int[] array;
    @AutoWired
    StudentParcelable studentParcelable;
    @AutoWired
    StudentParcelable[] studentParcelables;
    @AutoWired("users")
    List<StudentParcelable> studentParcelableList;
    @AutoWired
    StudentSerializable studentSerializable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        AutoWiredUtil.bind(this);
        Log.d("IntentActivity", "onCreate: num = " + num
                + ", name = " + name
                + ", isMale = " + isMale
                + ", array = " + Arrays.toString(array)
                + ", studentParcelable = " + studentParcelable
                + ", studentParcelables = " + studentParcelables
                + ", studentParcelableList = " + studentParcelableList
                + ", studentSerializable = " + studentSerializable);

    }
}
