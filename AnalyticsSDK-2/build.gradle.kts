plugins {
    alias(libs.plugins.android.library)
    id("maven-publish") // Apply the correct Maven Publish plugin
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}



publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.noam0" // Change to your GitHub username
            artifactId = "AnalyticsSDK" // Change to your library name
            version = "1.0.0" // Update when releasing new versions

            afterEvaluate {
                artifact(tasks.getByName("bundleReleaseAar")) // Package as AAR file
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

    implementation(libs.retrofit) // Updated to use version catalog
    implementation(libs.converter.gson) // Updated to use version catalog
}
