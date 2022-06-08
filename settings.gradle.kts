rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = "1.7.0"
        kotlin("js") version kotlinVersion

        val kfcVersion = "5.30.0"
        id("io.github.turansky.kfc.application") version kfcVersion
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

includeBuild("seskar")

include("test")
