allprojects {
    repositories {
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

tasks.wrapper {
    gradleVersion = "6.6"
    distributionType = Wrapper.DistributionType.ALL
}
