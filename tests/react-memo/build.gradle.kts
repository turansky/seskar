plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(kotlinWrappers.react)
    jsMainImplementation(kotlinWrappers.reactDom)
    jsMainImplementation(kotlinWrappers.reactUse)

    commonTestImplementation(libs.kotlin.test)
    commonTestImplementation(libs.coroutines.core)
    jsTestImplementation(projects.tests.reactTest)
    jsTestImplementation(kotlinWrappers.testingLibraryReact)
    jsTestImplementation(kotlinWrappers.testingLibraryUserEvent)
}
