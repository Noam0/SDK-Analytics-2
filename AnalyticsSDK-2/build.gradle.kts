plugins {
    alias(libs.plugins.android.library)
    id("maven-publish") // ✅ Ensure Maven Publish Plugin is applied
}

android {
    namespace = "com.example.analyticssdk_2"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17 // ✅ Set Java 17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

// ✅ Properly configured Maven Publishing
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.Noam0"  // ✅ Use the correct GitHub username
                artifactId = "AnalyticsSDK" // ✅ Use the correct library name
                version = "1.0.1"  // ✅ Update for new releases

                artifact(tasks.getByName("bundleReleaseAar")) // ✅ Publish AAR file
            }
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.retrofit) // ✅ Uses version catalog
    implementation(libs.converter.gson) // ✅ Uses version catalog
}
