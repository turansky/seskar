rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = "1.4-M3"
        kotlin("jvm") version kotlinVersion
        kotlin("js") version kotlinVersion
    }

    repositories {
        gradlePluginPortal()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

includeBuild("gradle-plugin")

include("example")
