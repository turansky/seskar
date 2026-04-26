rootProject.name = "seskar"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }

        create("kfc") {
            from(files("../gradle/kfc.versions.toml"))
        }
    }
}

include(":seskar-core")

include(":seskar-compiler-plugin")
include(":seskar-gradle-plugin")
