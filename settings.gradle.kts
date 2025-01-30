pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io") // Add JitPack repository
    }
}

rootProject.name = "AnalyticsSDK2"
include(":AnalyticsSDK-2") // Ensure it matches your module name
include(":myapplication")
