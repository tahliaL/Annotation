package com.tahlia.annotation.retention_source.apt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.tahlia.annotation.BindView;
import com.tahlia.annotation.R;

public class BindAptActivity extends AppCompatActivity {

    @BindView(R.id.tv_1)
    public TextView mFirstTv;

    @BindView(R.id.tv_2)
    public TextView mSecTv;

    @BindView(R.id.tv_3)
    public TextView mThirdTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_apt);
        BindUtil.bind(this);

        mFirstTv.setText("修改后的Text 1");
        mSecTv.setText("修改后的Text 2");
        mThirdTv.setText("修改后的Text 3");
    }
}
