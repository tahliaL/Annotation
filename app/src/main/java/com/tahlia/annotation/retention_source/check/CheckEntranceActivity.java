package com.tahlia.annotation.retention_source.check;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class CheckEntranceActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WeekdayUtil util = new WeekdayUtil();
        util.setDay(WeekdayUtil.MONDAY);
        // 传参仅可传入WEEKDAY注解上IntDef中声明的值，否则IDE和编译报错
        // util.setDay(WeekdayUtil.MAN);
        // util.setDay(1);
        // util.setDay(65535);
    }
}
