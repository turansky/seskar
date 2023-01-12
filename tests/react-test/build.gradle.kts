import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.kfc.wrappers")
    id("io.github.turansky.seskar")
}

val coroutinesVersion = project.property("kotlinx-coroutines.version") as String

dependencies {
    implementation(wrappers("react"))
    implementation(wrappers("react-dom"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    implementation(wrappers("react-dom-test-utils"))
}

tasks.withType<KotlinCompile<*>>().configureEach {
    kotlinOptions.freeCompilerArgs += listOf(
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
    )
}
