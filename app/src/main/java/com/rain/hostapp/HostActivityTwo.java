package com.rain.hostapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Author:rain
 * Date:2018/7/17 16:30
 * Description:
 */
public class HostActivityTwo extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        tv = findViewById(R.id.tv);
        tv.setText("HostActivityTwo");
    }
}
