import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    kotlin("jvm")
    id("io.github.turansky.kfc.maven-central-publish")
}

dependencies {
    compileOnly(kotlin("compiler-embeddable"))
}

// TODO: remove after migration
tasks.withType<KotlinCompile<*>>().configureEach {
    kotlinOptions.freeCompilerArgs += listOf(
        "-opt-in=org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi",
        "-opt-in=org.jetbrains.kotlin.backend.common.extensions.FirIncompatiblePluginAPI",
    )
}
