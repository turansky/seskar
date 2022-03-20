rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = "1.6.10"
        kotlin("js") version kotlinVersion

        val kfcVersion = "5.3.1"
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
