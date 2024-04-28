plugins {
    id("io.github.turansky.kfc.library")
}

dependencies {
    jsMainImplementation(project(":kotlin-js-union"))

    jsTestImplementation(kotlin("test-js"))
}
