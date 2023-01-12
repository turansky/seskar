import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.kfc.wrappers")
    id("io.github.turansky.seskar")
}

val coroutinesVersion = project.property("kotlinx-coroutines.version") as String

dependencies {
    implementation(project(":tests:data"))
    implementation("io.github.turansky.seskar:seskar-core")
    implementation(wrappers("react-dom"))

    testImplementation(kotlin("test-js"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    testImplementation(project(":tests:react-test"))
    testImplementation(wrappers("react-dom-test-utils"))
}

tasks.withType<KotlinCompile<*>>().configureEach {
    kotlinOptions.freeCompilerArgs += listOf(
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
    )
}
