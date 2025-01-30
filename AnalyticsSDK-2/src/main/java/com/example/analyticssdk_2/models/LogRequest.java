package com.example.analyticssdk_2.models;

public class LogRequest {
    private String appId;
    private String userId;
    private String logType; // e.g., "Crash"
    private String description; // e.g., stack trace or error message

    public LogRequest(String appId,String userId, String logType, String description) {
        this.appId = appId;
        this.userId = userId;
        this.logType = logType;
        this.description = description;
    }

    // Getters (if needed)
    public String getAppId() {
        return appId;
    }

    public String getLogType() {
        return logType;
    }

    public String getDescription() {
        return description;
    }

    public String getUserId() {
        return userId;
    }
}