plugins {
    kotlin("js") version "1.6.0"
    id("com.github.turansky.seskar") version "0.1.0"
}

kotlin.js {
    browser()

    binaries.executable()
}

dependencies {
    implementation("com.github.turansky.seskar:seskar-core:0.1.0")

    testImplementation(kotlin("test-js"))
}

tasks.wrapper {
    gradleVersion = "7.3"
}
