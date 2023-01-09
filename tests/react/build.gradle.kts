plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.kfc.wrappers")
    id("io.github.turansky.seskar")
}

dependencies {
    implementation(enforcedPlatform(wrappersBom()))
    implementation(wrappers("react"))
}
