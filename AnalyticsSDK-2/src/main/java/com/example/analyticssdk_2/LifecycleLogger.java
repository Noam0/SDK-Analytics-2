package com.example.analyticssdk_2;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.analyticssdk_2.endPoints.LocationManager;
import com.example.analyticssdk_2.endPoints.LogManager;
import com.example.analyticssdk_2.endPoints.UserManager;

public class LifecycleLogger implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        UserManager.createOrUpdateUser(activity.getApplicationContext());
        LogManager.sendLog("UserLogin", "App launched");
        if (LocationManager.checkLocationPermissions(activity)) {
            UserManager.sendGeolocation(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {}

    @Override
    public void onActivityResumed(Activity activity) {
        LogManager.sendLog("LifeCycle", "App resumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogManager.sendLog("LifeCycle", "App paused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogManager.sendLog("LifeCycle", "App Stopped");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogManager.sendLog("LifeCycle", "App Destroyed");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

    public static void init(Application application) {
        application.registerActivityLifecycleCallbacks(new LifecycleLogger());
    }


}