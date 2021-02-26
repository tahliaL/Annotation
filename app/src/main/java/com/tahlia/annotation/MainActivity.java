package com.tahlia.annotation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import com.tahlia.annotation.retention_runtime.bind.BindActivity;
import com.tahlia.annotation.retention_runtime.intent.IntentActivity;
import com.tahlia.annotation.retention_runtime.intent.StudentParcelable;
import com.tahlia.annotation.retention_runtime.intent.StudentSerializable;
import com.tahlia.annotation.retention_source.apt.AptActivity;
import com.tahlia.annotation.retention_source.apt.BindAptActivity;

import java.util.ArrayList;

@AllMethod
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button bindInvokeBtn = findViewById(R.id.btn_bind_invoke);
        bindInvokeBtn.setOnClickListener(this);
        Button intentBtn = findViewById(R.id.btn_intent);
        intentBtn.setOnClickListener(this);
        Button bindAptBtn = findViewById(R.id.btn_bind_apt);
        bindAptBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind_invoke:
                startActivity(new Intent(MainActivity.this, BindActivity.class));
                break;
            case R.id.btn_intent:
                ArrayList<StudentParcelable> stuParcelableList = new ArrayList<>();
                stuParcelableList.add(new StudentParcelable("张三"));

                Intent intent = new Intent(MainActivity.this, IntentActivity.class);
                intent.putExtra("name", "张三")
                        .putExtra("isMale", true)
                        .putExtra("arr", new int[]{1,2,3,4,5,6})
                        .putExtra("studentParcelable", new StudentParcelable("张三"))
                        .putExtra("studentParcelables", new StudentParcelable[]{new StudentParcelable("张三"), new StudentParcelable("李四")})
                        .putExtra("users", stuParcelableList)
                        .putExtra("studentSerializable", new StudentSerializable("张三"));
                startActivity(intent);
                break;
            case R.id.btn_bind_apt:
                startActivity(new Intent(MainActivity.this, BindAptActivity.class));
                break;
        }
    }


//    @OnClick({R.id.GoIntent})
//    private void onClickIntent(View view) {
//        switch (view.getId()) {
//            case R.id.GoIntent:
//                Log.d("lys", "onClickIntent: GoIntent");
//                break;
//            case R.id.NoIntent:
//                Log.d("lys", "onClickIntent: NoIntent");
//                break;
//        }
//    }
//
//    @OnLongClick({R.id.NoIntent})
//    private boolean onLongClick(View view) {
//        switch (view.getId()) {
//            case R.id.GoIntent:
//                Log.d("lys", "onLongClick: GoIntent");
//                break;
//            case R.id.NoIntent:
//                Log.d("lys", "onLongClick: NoIntent");
//                break;
//        }
//        return false;
//    }
}
