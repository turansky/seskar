plugins {
    kotlin("multiplatform")
    id("io.github.turansky.kfc.library")
}

dependencies {
    jsTestImplementation(kotlin("test-js"))
}
