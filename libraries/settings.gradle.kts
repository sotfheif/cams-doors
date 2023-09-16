@file:Suppress("UnstableApiUsage")

rootProject.name = "androidx-compose-material3-pullrefresh"

pluginManagement {
    val localProperties = java.util.Properties().apply {
        val file = file("local.properties")
        if (!file.exists()) file.createNewFile()
        load(file.inputStream())
    }
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.0.2"
        id("com.android.library") version "8.0.2"
        id("com.gradle.enterprise") version "3.13.2"
        kotlin("android") version "1.8.20"


    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.gradle.enterprise") apply false
}

if (gradle.parent == null) {
    apply(plugin = "com.gradle.enterprise")
    gradleEnterprise {
        buildScan {
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
            publishAlwaysIf(!gradle.startParameter.isOffline)
        }
    }

    include("test-app")
}


include("library")
