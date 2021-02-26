package com.tahlia.annotation.retention_source.apt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.tahlia.annotation.HelloWorld;
import com.tahlia.annotation.R;

public class AptActivity extends AppCompatActivity {

    @HelloWorld()
    public TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apt);
    }
}
