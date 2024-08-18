plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.js.plain.objects) apply false
}

tasks.wrapper {
    gradleVersion = "8.10"
}
