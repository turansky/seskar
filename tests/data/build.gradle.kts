plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.seskar")
}

val kotlinWrappersVersion = property("kotlin-wrappers.version") as String

dependencies {
    implementation("io.github.turansky.seskar:seskar-core")
    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
}
