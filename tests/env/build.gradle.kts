plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

bundlerEnvironment {
    set("BUILD_NUMBER", "generic-number")
    set("NUMBER", "42")
}

dependencies {
    commonTestImplementation(libs.kotlin.test)
}
