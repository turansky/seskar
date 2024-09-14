plugins {
    alias(kfc.plugins.application)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(kotlinWrappers.emotion)
    jsMainImplementation(kotlinWrappers.react)
    jsMainImplementation(kotlinWrappers.reactDom)

    jsMainImplementation(projects.tests.reactLazyComponents.content)
    jsMainImplementation(projects.tests.reactLazyComponents.header)
    jsMainImplementation(projects.tests.reactLazyComponents.footer)
}
