pluginManagement {
    repositories {
        google()     // Google's repository for Android libraries
        mavenCentral() // Maven Central for other dependencies
        gradlePluginPortal() // Gradle plugin portal for plugins
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PlayerFinder"
include(":app")
