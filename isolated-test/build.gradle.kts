plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.js.plain.objects) apply false
    alias(libs.plugins.kfc.application)
    alias(libs.plugins.seskar)
}

dependencies {
    jsTestImplementation(libs.kotlin.test.js)
}

tasks.wrapper {
    gradleVersion = "8.10"
}
