rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = "1.4.32"
        kotlin("jvm") version kotlinVersion
        kotlin("js") version kotlinVersion
    }
}

includeBuild("gradle-plugin")

include("example")
