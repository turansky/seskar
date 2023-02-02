plugins {
    kotlin("js") version "1.8.10"
    id("io.github.turansky.kfc.application") version "5.100.0"
    id("io.github.turansky.seskar") version "1.0.0"
}

dependencies {
    implementation("io.github.turansky.seskar:seskar-core:1.0.0")

    testImplementation(kotlin("test-js"))
}

tasks.wrapper {
    gradleVersion = "7.6"
}
