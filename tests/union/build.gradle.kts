plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    commonMainImplementation(kotlinWrappers.web)

    commonTestImplementation(libs.kotlin.test)
}
