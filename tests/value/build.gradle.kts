plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.kfc.wrappers")
    id("io.github.turansky.seskar")
}

dependencies {
    jsMainImplementation(project(":tests:union-data"))

    jsMainImplementation("io.github.turansky.seskar:seskar-core")
    jsMainImplementation(wrappers("web"))

    jsTestImplementation(kotlin("test-js"))
}
