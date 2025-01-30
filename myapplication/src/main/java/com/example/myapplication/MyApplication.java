package com.example.myapplication;

import android.app.Application;

import com.example.analyticssdk_2.AnalyticsSDK;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Analytics SDK
        AnalyticsSDK.initialize(this);
    }
}
