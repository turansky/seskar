plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.kfc.wrappers")
    id("io.github.turansky.seskar")
}

dependencies {
    implementation("io.github.turansky.seskar:seskar-core")
    implementation(enforcedPlatform(wrappersBom()))
    implementation(wrappers("react"))
}
