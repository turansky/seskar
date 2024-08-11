plugins {
    alias(libs.plugins.kfc.application)
    alias(libs.plugins.seskar)
}

dependencies {
    jsTestImplementation(libs.kotlin.test.js)
}

tasks.patchBundlerConfig {
    env("BUILD_NUMBER", "generic-number")
    env("NUMBER", "42")
}
