import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    kotlin("jvm")
    id("io.github.turansky.kfc.maven-central-publish")
}

dependencies {
    compileOnly(kotlin("compiler-embeddable"))
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    compilerOptions.freeCompilerArgs.addAll(
        "-opt-in=org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi",
        "-opt-in=org.jetbrains.kotlin.ir.symbols.UnsafeDuringIrConstructionAPI",
    )
}
