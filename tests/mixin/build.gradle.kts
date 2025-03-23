plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(projects.tests.unionData)

    commonMainImplementation(kotlinWrappers.web)

    commonTestImplementation(libs.kotlin.test)
}
