plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    commonMainImplementation(kotlinWrappers.js)
    commonMainImplementation(kotlinWrappers.web)

    commonTestImplementation(libs.kotlin.test)
}
