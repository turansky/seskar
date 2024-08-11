plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.kfc.wrappers")
    id("io.github.turansky.seskar")
}

dependencies {
    jsMainImplementation(wrappers("react"))
    jsMainImplementation(wrappers("react-dom"))
    jsMainImplementation(wrappers("react-use"))

    jsTestImplementation(libs.kotlin.test.js)
    jsTestImplementation(libs.coroutines.core)
    jsTestImplementation(libs.coroutines.test)
    jsTestImplementation(project(":tests:react-test"))
    jsTestImplementation(wrappers("react-dom-test-utils"))
}
