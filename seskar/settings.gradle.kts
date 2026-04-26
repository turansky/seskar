rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kfcVersion = extra["kfc.version"] as String
        id("io.github.turansky.kfc.library") version kfcVersion
        id("io.github.turansky.kfc.maven-central-publish") version kfcVersion
        id("io.github.turansky.kfc.plugin-publish") version kfcVersion
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        register("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

include(":seskar-core")

include(":seskar-compiler-plugin")
include(":seskar-gradle-plugin")
