plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.kfc.wrappers")
    id("io.github.turansky.seskar")
}

val coroutinesVersion = project.property("kotlinx-coroutines.version") as String

dependencies {
    jsMainImplementation("io.github.turansky.seskar:seskar-core")
    jsMainImplementation(wrappers("js"))

    jsTestImplementation(kotlin("test-js"))
    jsTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")
    jsTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}
