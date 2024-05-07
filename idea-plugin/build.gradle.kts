plugins {
    kotlin("jvm") version "1.9.24"
    id("org.jetbrains.intellij") version "1.17.3"
    id("io.github.turansky.kfc.version") version "7.60.0"
}

repositories {
    mavenCentral()
}

intellij {
    pluginName = "seskar"

    type = "IU"
    version = "2024.1"

    plugins = listOf(
        "java",
        "org.jetbrains.kotlin",
        "JavaScript"
    )
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    runIde {
        jvmArgs(
            "-Xms1g",
            "-Xmx4g"
        )
    }

    patchPluginXml {
        sinceBuild = "232.10300"
        untilBuild = "242.*"
    }

    publishPlugin {
        token = project.property("intellij.publish.token") as String
    }

    buildSearchableOptions {
        enabled = false
    }

    wrapper {
        gradleVersion = "8.7"
    }
}
