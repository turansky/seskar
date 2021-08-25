plugins {
    kotlin("js") version "1.5.30"
    id("com.github.turansky.seskar") version "0.1.0"
}

repositories {
    mavenCentral()
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
    gradleVersion = "7.2"
}
