plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.kfc.wrappers")
    id("io.github.turansky.seskar")
}

dependencies {
    jsMainImplementation(wrappers("react"))
    jsMainImplementation(wrappers("react-dom"))
    jsMainImplementation(libs.coroutines.core)
    jsMainImplementation(libs.coroutines.test)
    jsMainImplementation(wrappers("react-dom-test-utils"))
}
