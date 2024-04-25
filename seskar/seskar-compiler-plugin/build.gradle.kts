import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    kotlin("jvm")
    id("io.github.turansky.kfc.maven-central-publish")
}

kotlin {
    sourceSets {
        val main by getting {
            kotlin.srcDirs("../../idea-plugin/src/main/kotlin")
        }
    }
}

dependencies {
    compileOnly(kotlin("compiler-embeddable"))
}

// TODO: remove after migration
tasks.withType<KotlinCompile<*>>().configureEach {
    kotlinOptions.freeCompilerArgs += listOf(
        "-opt-in=org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi",
    )
}
