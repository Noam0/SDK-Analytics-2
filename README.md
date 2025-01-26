<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SDK Analytics Java - Integration Guide</title>
</head>
<body>

<h1>📌 SDK Analytics Java - Integration Guide</h1>

<p>This guide explains how to integrate the <strong>SDK Analytics Java</strong> library into your Android app using <strong>JitPack</strong>.</p>

<hr>

<h2>✅ 1️⃣ Add the JitPack Repository</h2>

<p>Since the SDK is hosted on JitPack, you must add its repository to your <code>settings.gradle.kts</code>.</p>

<h3>📌 Modify <code>settings.gradle.kts</code></h3>
<pre><code>
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }  <!-- ✅ Required for JitPack -->
    }
}
</code></pre>

<hr>

<h2>✅ 2️⃣ Add the SDK Dependency</h2>

<p>Add the SDK dependency to your <code>app-level build.gradle.kts</code>.</p>

<h3>📌 Modify <code>app/build.gradle.kts</code></h3>
<pre><code>
dependencies {
    implementation("com.github.Noam0:SDK-analytics-java:1.0") <!-- ✅ Use the latest version -->
}
</code></pre>

<hr>

<h2>✅ 3️⃣ Update <code>AndroidManifest.xml</code></h2>

<p>Your app <strong>must</strong> allow Internet access and cleartext traffic <strong>for local testing</strong>.</p>

<h3>📌 Modify <code>AndroidManifest.xml</code></h3>
<pre><code>
&lt;uses-permission android:name="android.permission.INTERNET" /&gt;

&lt;application
    android:usesCleartextTraffic="true"&gt;  <!-- ✅ Required to allow HTTP requests -->
&lt;/application&gt;
</code></pre>

<p>⚠️ <strong>Without this setting, the SDK cannot send API requests!</strong></p>
<p>If your backend uses <strong>HTTPS</strong>, this setting is <strong>not required</strong>.</p>

<hr>

<h2>✅ 4️⃣ Update <code>compileSdk</code> & <code>targetSdk</code></h2>

<p>To prevent compatibility issues, you <strong>must</strong> update your <code>compileSdk</code> and <code>targetSdk</code> to <strong>35</strong>.</p>

<h3>📌 Modify <code>app/build.gradle.kts</code></h3>
<pre><code>
android {
    compileSdk = 35  <!-- ✅ Required -->

    defaultConfig {
        minSdk = 24
        targetSdk = 35  <!-- ✅ Required -->
    }
}
</code></pre>

<p>⚠️ <strong>Failure to update these values may result in build errors!</strong></p>

<hr>

<h2>✅ 5️⃣ Add Required Dependencies</h2>

<p>Your app <strong>must</strong> include the same dependencies as the SDK.</p>

<h3>📌 Add to <code>app/build.gradle.kts</code></h3>
<pre><code>
dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}
</code></pre>

<p>⚠️ <strong>Failure to include these will cause runtime crashes (<code>NoClassDefFoundError</code>).</strong></p>

<hr>

<h2>✅ 6️⃣ Initialize the SDK in Your App</h2>

<p>To use the SDK, initialize it inside your <code>Application</code> class.</p>

<h3>📌 Create <code>MyApplication.kt</code></h3>
<pre><code>
package com.example.homie

import android.app.Application
import com.example.analyticssdk_2.AnalyticsSDK

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // ✅ Initialize Analytics SDK with Base URL & App Name
        AnalyticsSDK.initialize(this, "http://10.0.2.2:5000/", "homiz")  // ⚠️ Replace with your API
    }
}
</code></pre>

<p>📌 <strong>Note:</strong></p>
<ul>
    <li>Replace <code>"http://10.0.2.2:5000/"</code> with your actual API base URL.</li>
    <li><code>"homiz"</code> is the <strong>application identifier</strong> (change as needed).</li>
</ul>

<hr>

<h2>✅ 7️⃣ Track Events in Your App</h2>

<p>Once initialized, use the SDK to track analytics events.</p>

<h3>📌 Track User Actions</h3>
<pre><code>
AnalyticsSDK.trackEvent("UserLoggedIn")
</code></pre>

<h3>📌 Log User Actions with Parameters</h3>
<pre><code>
val eventData = mapOf(
    "screen" to "Home",
    "button" to "Start"
)
AnalyticsSDK.trackEvent("ButtonClicked", eventData)
</code></pre>

<hr>

<h2>✅ 8️⃣ Troubleshooting & Common Issues</h2>

<h3>🔴 <strong>"Unable to parse TLS packet header"</strong></h3>
<p>✅ <strong>Solution:</strong> Ensure <code>AndroidManifest.xml</code> contains:</p>
<pre><code>
&lt;application android:usesCleartextTraffic="true"&gt;
</code></pre>
<p>If your server uses <strong>HTTPS</strong>, use a valid SSL certificate.</p>

<h3>🔴 <strong>"Retrofit$Builder Class Not Found"</strong></h3>
<p>✅ <strong>Solution:</strong> Make sure your <strong>app</strong> (not just the SDK) includes <strong>Retrofit dependencies</strong>:</p>
<pre><code>
dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}
</code></pre>

<h3>🔴 <strong>"NoClassDefFoundError" or Missing Library Classes</strong></h3>
<p>✅ <strong>Solution:</strong> If using <strong>ProGuard</strong>, add the following rules to <code>proguard-rules.pro</code>:</p>
<pre><code>
-keep class retrofit2.** { *; }
-keep class com.google.gson.** { *; }
-keep class okhttp3.** { *; }
</code></pre>

<h3>🔴 <strong>"Failed to fetch data" when making API requests</strong></h3>
<p>✅ <strong>Solution:</strong> Check if your <strong>local backend is running</strong> by executing:</p>
<pre><code>
curl http://10.0.2.2:5000/
</code></pre>
<p>If it fails, restart the server.</p>

<hr>

<h2>✅ 9️⃣ Conclusion</h2>

<p>You now have <strong>SDK Analytics Java</strong> fully integrated! 🚀</p>

<h3>✅ <strong>Quick Recap</strong></h3>
<ul>
    <li>✅ Add JitPack to your project.</li>
    <li>✅ Add SDK dependency to <code>build.gradle.kts</code>.</li>
    <li>✅ Allow Internet permissions & cleartext traffic in <code>AndroidManifest.xml</code>.</li>
    <li>✅ Ensure <code>compileSdk = 35</code> and <code>targetSdk</code> are updated.</li>
    <li>✅ Include Retrofit dependencies to prevent missing class errors.</li>
    <li>✅ Initialize SDK in <code>MyApplication.kt</code>.</li>
    <li>✅ Track analytics events using <code>AnalyticsSDK.trackEvent(...)</code>.</li>
</ul>

<hr>

<h2>🎯 Next Steps</h2>

<p>✅ <strong>Test with real data</strong> – Run your app and <strong>monitor analytics events</strong>.</p>
<p>✅ <strong>Enhance SDK features</strong> – Add more tracking features.</p>
<p>✅ <strong>Deploy in production</strong> – Ensure HTTPS is enabled before live use.</p>

</body>
</html>
