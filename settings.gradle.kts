@file:Suppress("UnstableApiUsage")

rootProject.name = "xxl-job"
pluginManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/public")
    }
}

include(
    ":xxl-job-admin",
    ":xxl-job-core",
)
