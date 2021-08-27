rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = "1.5.30"
        kotlin("js") version kotlinVersion

        val kfcVersion = "4.30.0"
        id("com.github.turansky.kfc.application") version kfcVersion
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

includeBuild("seskar")

include("test")
