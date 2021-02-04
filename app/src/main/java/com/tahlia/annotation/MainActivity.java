package com.tahlia.annotation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tahlia.annotation.annotation.BindViewReflect;
import com.tahlia.annotation.annotationUtil.BindUtils;

@AllMethod
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_hello_world)
    private TextView mainTv;

    @BindViewReflect(R.id.GoIntent)
    private Button mGoIntent;

    @BindViewReflect(R.id.NoIntent)
    private Button mNoIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindUtils.Bind(this);
        mainTv.setText("冲冲冲！");

        mGoIntent.setOnClickListener(this);
        mNoIntent.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.GoIntent:
                Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
                intent1.putExtra("strTest1", 223);
                intent1.putExtra("strTese2", true);
                intent1.putExtra("str", "hahhahah");
                intent1.putExtra("strTest4", "wuhu~");
                startActivity(intent1);
                break;
            case R.id.NoIntent:
                startActivity(new Intent(MainActivity.this, Main3Activity.class));
                break;
        }
    }
}
