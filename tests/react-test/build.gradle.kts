plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.kfc.wrappers")
    id("io.github.turansky.seskar")
}

val coroutinesVersion = project.property("kotlinx-coroutines.version") as String

dependencies {
    jsMainImplementation(wrappers("react"))
    jsMainImplementation(wrappers("react-dom"))
    jsMainImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")
    jsMainImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    jsMainImplementation(wrappers("react-dom-test-utils"))
}
