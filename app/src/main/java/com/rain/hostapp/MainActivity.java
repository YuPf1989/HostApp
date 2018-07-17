package com.rain.hostapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_WRITE = 341;
    private static final String TAG  = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_loadPlugin).setOnClickListener(this);
        if (hasPermission()) {
            loadPlugin();
        } else {
            requestPermission();
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_WRITE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (REQUEST_CODE_WRITE == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadPlugin();
            } else {
                Log.e(TAG, "onRequestPermissionsResult: 未授予权限");
            }
        }
    }

    private void loadPlugin() {
        File file = new File(Environment.getExternalStorageDirectory(), "Test22.apk");
        if (file.exists()) {
            try {
                PluginManager.getInstance(this).loadPlugin(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "plugin不存在", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean hasPermission() {
        // 安卓6.0需要动态检查特殊权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            return checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loadPlugin:
                String pkg = "com.rain.pluginapp";
                if (PluginManager.getInstance(this).getLoadedPlugin(pkg) != null) {
                    Intent intent = new Intent();
                    intent.setClassName(this, "com.rain.pluginapp.ActivityTwo");
                    startActivity(intent);
                }
                break;
        }
    }
}
