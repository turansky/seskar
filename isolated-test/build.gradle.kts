plugins {
    kotlin("js") version "1.5.0"
    id("com.github.turansky.seskar") version "0.0.11"
}

repositories {
    mavenCentral()
}

kotlin.js {
    browser()

    binaries.executable()
}

dependencies {
    implementation("com.github.turansky.seskar:seskar-core:0.0.11")

    testImplementation(kotlin("test-js"))
}

tasks.wrapper {
    gradleVersion = "7.0.2"
    distributionType = Wrapper.DistributionType.ALL
}
