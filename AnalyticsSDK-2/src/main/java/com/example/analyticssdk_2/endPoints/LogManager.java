package com.example.analyticssdk_2.endPoints;


import static com.example.analyticssdk_2.AnalyticsSDK.getUserId;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.analyticssdk_2.AnalyticsSDK;
import com.example.analyticssdk_2.models.LogRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LogManager {




    /**
     * Sends a log to the server.
     *
     * @param logType     The type of log (e.g., "Crash").
     * @param description The log description (e.g., stack trace or error details).
     */
    public static void sendLog(String logType, String description) {

        String userId = getUserId();
        userId = (userId != null) ? userId : "DefaultUserId";
        Log.d("UserIDLOG", userId);

        String appId = AnalyticsSDK.getInstance().getAppId();

        LogRequest logRequest = new LogRequest(appId, userId, logType, description);

        AnalyticsSDK.getInstance().getApiService().createLog("application/json", logRequest)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Log.d("LogManager", "Log sent successfully!");
                        } else {
                            Log.e("LogManager", "Failed to send log: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("LogManager", "Error sending log: " + t.getMessage());
                    }
                });
    }



}