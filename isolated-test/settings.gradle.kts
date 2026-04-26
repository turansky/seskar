rootProject.name = "seskar-isolated-test"

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
