plugins {
    alias(libs.plugins.android.library)
    id("maven-publish") // ✅ Ensure Maven Publish Plugin is applied
}

android {
    namespace = "com.example.analyticssdk_2"
    compileSdk = 34 // ✅ Update to latest version

    defaultConfig {
        minSdk = 26
        targetSdk = 35  // ✅ Ensure compatibility with dependencies
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

// ✅ Correctly Publishing Dependencies with Maven
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.Noam0"  // ✅ Use the correct GitHub username
                artifactId = "AnalyticsSDK" // ✅ Use the correct library name
                version = "1.0.14"  // ✅ Update for new releases

                artifact(tasks.getByName("bundleReleaseAar")) // ✅ Publish AAR file

                pom {
                    name.set("Analytics SDK")
                    description.set("An analytics SDK for Android applications.")
                    url.set("https://github.com/Noam0/AnalyticsSDK")

                    // ✅ Ensure dependencies are included in POM file for JitPack users
                    withXml {
                        val dependenciesNode = asNode().appendNode("dependencies")

                        configurations.api.get().dependencies.forEach { dependency ->
                            val dependencyNode = dependenciesNode.appendNode("dependency")
                            dependencyNode.appendNode("groupId", dependency.group)
                            dependencyNode.appendNode("artifactId", dependency.name)
                            dependencyNode.appendNode("version", dependency.version)
                            dependencyNode.appendNode("scope", "compile") // ✅ Correct scope
                        }

                        configurations.implementation.get().dependencies.forEach { dependency ->
                            val dependencyNode = dependenciesNode.appendNode("dependency")
                            dependencyNode.appendNode("groupId", dependency.group)
                            dependencyNode.appendNode("artifactId", dependency.name)
                            dependencyNode.appendNode("version", dependency.version)
                            dependencyNode.appendNode("scope", "runtime") // ✅ Runtime scope
                        }
                    }
                }
            }
        }
    }
}

// ✅ Ensure dependencies are included for library users
dependencies {
    // ✅ Ensure these dependencies are available for SDK users
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.google.android.gms:play-services-location:21.0.1")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
