rootProject.name = "seskar"

pluginManagement {
    plugins {
        kotlin("jvm") version "1.4-M3"
    }

    repositories {
        gradlePluginPortal()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

includeBuild("gradle-plugin")

include("example")
