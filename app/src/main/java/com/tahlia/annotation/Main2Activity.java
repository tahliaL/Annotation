package com.tahlia.annotation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.tahlia.annotation.allmethod.AllMehtodClz;
import com.tahlia.annotation.annotation.IntentExtra;
import com.tahlia.annotation.annotationUtil.ExtraUtils;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";
    
    public int i;
    private int j;
    protected int k;

    @IntentExtra
    public int strTest1;
    @IntentExtra("")
    public boolean strTese2;
    @IntentExtra("str")
    private String strTest3;
    @IntentExtra
    private String strTest4;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate: i = "+i+", j = "+ j+", k = "+k+", strTest1 = "+strTest1+", strTest2 = "+strTese2+", strTest3 = "+strTest3+", strTest4 = "+strTest4);
        ExtraUtils.parse(this);
        Log.d(TAG, "onCreate: i = "+i+", j = "+ j+", k = "+k+", strTest1 = "+strTest1+", strTest2 = "+strTese2+", strTest3 = "+strTest3+", strTest4 = "+strTest4);
    }
}
