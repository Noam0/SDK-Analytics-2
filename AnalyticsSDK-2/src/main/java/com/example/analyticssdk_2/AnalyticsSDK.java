package com.example.analyticssdk_2;

import android.app.Application;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.analyticssdk_2.endPoints.LogManager;
import com.example.analyticssdk_2.endPoints.UserManager;
import com.example.analyticssdk_2.endPoints.AppRatingsManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnalyticsSDK {
    private static AnalyticsSDK instance;
    private final ApiService apiService;
    private final String appId;
    private final String baseUrl;
    private static Context appContext;

    private static final String PREFS_NAME = "AnalyticsPrefs";
    private static final String USER_ID_KEY = "user_id";

    // Constructor to initialize ApiService and appId
    private AnalyticsSDK(Context context) {
        this.appContext = context.getApplicationContext();
        this.appId = context.getPackageName();

        this.baseUrl = "http://10.0.2.2:5000/";
        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.apiService = retrofit.create(ApiService.class);

        // Initialize logging
        CrashLogger.init();
        LifecycleLogger.init((Application) context);

        // Auto register or retrieve user
        //UserManager.autoRegisterUser(context);
    }

    // Initialize the AnalyticsSDK
    public static void initialize(Context context) {
        if (instance == null) {
            instance = new AnalyticsSDK(context);
        }

    }

    // Get the singleton instance of AnalyticsSDK
    public static AnalyticsSDK getInstance() {
        if (instance == null) {
            throw new IllegalStateException("AnalyticsSDK is not initialized. Call initialize() first.");
        }
        return instance;
    }



    // Getter for appId
    public String getAppId() {
        return appId;
    }

    // Getter for ApiService
    public ApiService getApiService() {
        return apiService;
    }

   // public static void registerUser(String userId, String firstSeen, String lastSeen) {
    // UserManager.registerUser(userId, firstSeen, lastSeen);
    //}

    // Utility method to show the rating dialog
    public static void showRatingDialog(Activity activity) {
        AppRatingsManager.showRatingDialog(activity);
    }

    // public static void updateUserLastSeen() {
    //    UserManager.updateLastSeen(appContext);
    //}

    public static void logCrash(String crashDetails) {
        LogManager.sendLog("Crash", crashDetails);
    }

    public static void createLog(String logType, String description) {
        LogManager.sendLog(logType, description);
    }

    public static String getUserId() {
        SharedPreferences prefs = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER_ID_KEY, null);
    }



}