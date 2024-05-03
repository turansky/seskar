plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.kfc.wrappers")
    id("io.github.turansky.seskar")
}

dependencies {
    jsMainImplementation("io.github.turansky.seskar:seskar-core")
    jsMainImplementation(wrappers("js"))

    jsTestImplementation(kotlin("test-js"))
}
