plugins {
    kotlin("js") version "1.6.10"
    id("io.github.turansky.seskar") version "0.4.0"
}

kotlin.js {
    browser()

    binaries.executable()
}

dependencies {
    implementation("io.github.turansky.seskar:seskar-core:0.4.0")

    testImplementation(kotlin("test-js"))
}

tasks.wrapper {
    gradleVersion = "7.3.3"
}
