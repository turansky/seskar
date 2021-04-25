rootProject.name = "seskar"

pluginManagement {
    plugins {
        id("com.gradle.plugin-publish") version "0.14.0"
        id("com.github.turansky.kfc.plugin-publish") version "3.6.1"

        val kotlinVersion = "1.4.32"
        kotlin("jvm") version kotlinVersion
        kotlin("js") version kotlinVersion
    }
}

include(":gradle-plugin")
