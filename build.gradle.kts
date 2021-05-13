plugins {
    kotlin("js") apply false
}

subprojects {
    repositories {
        mavenCentral()
    }
}

tasks.wrapper {
    gradleVersion = "7.0.1"
    distributionType = Wrapper.DistributionType.ALL
}
