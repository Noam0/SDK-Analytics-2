# 📊 Analytics SDK

## 🚀 Overview
The **Analytics SDK** provides seamless tracking and real-time logging for Android applications. Designed for minimal integration effort, this SDK enables developers to collect vital user analytics without writing extra tracking code.

<div style="display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/user-attachments/assets/481fdcad-caf8-412a-8090-3d500d91ec4b" width="500" height="200">
    <img src="https://github.com/user-attachments/assets/2aaca6d7-6607-4acf-89f6-39a9c61bfd64" width="250" height="250">
</div>


## 🛠️ Features
- **User Analytics**: Track total users, daily logins, session duration, and retention rates.  
- **Event Logging**: Monitor key user interactions like button clicks, page views, and feature usage.  
- **Crash & Error Tracking**: Detect application crashes and API request failures in real-time.  
- **Geolocation Analytics**: Log geographical data for improved regional insights.  
- **Secure Data Handling**: Stored logs are encrypted and efficiently managed.
- **App Rating Analytics**: Collect and analyze user feedback through app ratings and comments.
- **JitPack Compatible**: Easily integrate via **JitPack** with minimal configuration.



## 📦 Installation
To use this SDK, add the dependency to your **Android project** using JitPack:
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.YourRepo:AnalyticsSDK:1.0.14'
}
```

## 🚀 Getting Started
### **1️⃣ Initialize the SDK**
```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Analytics SDK
        AnalyticsSDK.initialize(this);
    }
}
```

### **2️⃣ Required Permissions**
Add the following permissions to your **AndroidManifest.xml** file:
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    <application
        android:usesCleartextTraffic="true">
        <!-- Other configurations -->
    </application>
</manifest>
```

### **3️⃣ Log Events**
Easily log different types of events with just one line of code:
```java
AnalyticsSDK.createLog("ButtonClicked", "User Clicked On Button");
AnalyticsSDK.createLog("UserLogin", "User logged into the app");
AnalyticsSDK.createLog("Crash", "App crashed due to NullPointerException");
AnalyticsSDK.createLog("APIRequestFailed", "Failed to fetch user data");
AnalyticsSDK.createLog("FeatureUsed", "User accessed analytics dashboard");
AnalyticsSDK.createLog("PageViewed", "User viewed the settings page");
AnalyticsSDK.createLog("DataExport", "User exported activity data");
```

## ⭐ App Rating Integration  
Easily prompt users to rate your app with just one line of code:  
```java
AnalyticsSDK.showRatingDialog(this);
````
<img src="https://github.com/user-attachments/assets/e9d23026-d259-4429-a2c7-28ed9690b9d5" width="200" height="450">

 
## 📸 Your Web Page Analytics Screenshots
Below are images showcasing the SDK in action:
![Image](https://github.com/user-attachments/assets/481fdcad-caf8-412a-8090-3d500d91ec4b)

![Image](https://github.com/user-attachments/assets/bd6608cf-6c12-460f-a0f4-603dffc83abe)

![Image](https://github.com/user-attachments/assets/adc9001a-3710-4a69-87e9-23e2b003e578)

## 🏆 Why Use This SDK?
- **📊 Powerful Analytics**: Understand how users interact with your app.
- **🚀 Effortless Integration**: Works out-of-the-box with minimal setup.
- **🔥 Automatic Crash Logging**: Detects & reports crashes instantly.
- **🛡 Secure & Scalable**: Data is encrypted & optimized for large-scale apps.
- **🎯 JitPack Support**: No manual downloads – just add the dependency!

## 🤝 Contributing
Contributions are welcome! Feel free to submit issues or open pull requests.

## 📜 License
This project is licensed under the **MIT License**.

---
📌 **Maintained by:** Noam Ben Binyamin & Yosi Ben Shushan 🔥

