plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    commonMainImplementation(libs.seskar.core)
    commonMainImplementation(kotlinWrappers.js)
    jsMainImplementation(kotlinWrappers.web)

    commonTestImplementation(libs.kotlin.test)
}
