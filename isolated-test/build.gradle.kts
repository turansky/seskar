plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jsPlainObjects) apply false
    alias(kfc.plugins.application)
    alias(libs.plugins.seskar)
}

dependencies {
    commonTestImplementation(libs.kotlin.test)
}

tasks.wrapper {
    gradleVersion = "9.2.1"
}
