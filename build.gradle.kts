plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jsPlainObjects) apply false
}

tasks.wrapper {
    gradleVersion = "9.0.0"
}
