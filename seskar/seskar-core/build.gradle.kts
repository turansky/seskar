plugins {
    id("io.github.turansky.kfc.multiplatform")
    id("io.github.turansky.kfc.maven-central-publish")
}

kotlin {
    iosArm32()
    iosArm64()
    iosX64()
    iosSimulatorArm64()
}
