plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.seskar")
}

val coroutinesVersion = project.property("kotlinx-coroutines.version") as String

dependencies {
    implementation(project(":tests:data"))
    implementation("io.github.turansky.seskar:seskar-core")

    testImplementation(kotlin("test-js"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}
