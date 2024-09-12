plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(projects.tests.unionData)

    jsMainImplementation(kotlinWrappers.web)

    jsTestImplementation(libs.kotlin.testJs)
}
