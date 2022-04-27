plugins {
    kotlin("js") version "1.6.21"
    id("io.github.turansky.kfc.application") version "5.9.1"
    id("io.github.turansky.seskar") version "0.5.0"
}

dependencies {
    implementation("io.github.turansky.seskar:seskar-core:0.5.0")

    testImplementation(kotlin("test-js"))
}

tasks.wrapper {
    gradleVersion = "7.4.2"
}
