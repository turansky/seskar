rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = "1.5.0"
        kotlin("js") version kotlinVersion

        val kfcVersion = "4.2.2"
        id("com.github.turansky.kfc.application") version kfcVersion
    }
}

includeBuild("seskar")

include("test")
