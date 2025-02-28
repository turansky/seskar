plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jsPlainObjects) apply false
    alias(kfc.plugins.application)
    alias(libs.plugins.seskar)
}

dependencies {
    jsTestImplementation(libs.kotlin.testJs)
}

tasks.wrapper {
    gradleVersion = "8.13"
}
