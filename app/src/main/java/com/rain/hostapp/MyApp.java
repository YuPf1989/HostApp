package com.rain.hostapp;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;

/**
 * Author:rain
 * Date:2018/7/16 16:19
 * Description:
 */
public class MyApp extends Application {
    private static Application instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 初始化didi pluginManager
        PluginManager.getInstance(base).init();

    }

    public static Application getApp() {
        return instance;
    }
}
