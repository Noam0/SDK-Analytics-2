package com.example.analyticssdk_2;

import android.util.Log;

import com.example.analyticssdk_2.AnalyticsSDK;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CrashLogger implements Thread.UncaughtExceptionHandler {
    private final Thread.UncaughtExceptionHandler defaultHandler;

    public CrashLogger() {
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        // Convert stack trace to string
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        String crashDetails = sw.toString();

        // Log the crash
        Log.e("CrashLogger", "Uncaught exception detected!", throwable);
        AnalyticsSDK.logCrash(crashDetails);

        // Call the system's default handler
        if (defaultHandler != null) {
            defaultHandler.uncaughtException(thread, throwable);
        }
    }

    public static void init() {
        Thread.setDefaultUncaughtExceptionHandler(new CrashLogger());
    }
}
