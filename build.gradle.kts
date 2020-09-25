allprojects {
    repositories {
        jcenter()
    }
}

tasks.wrapper {
    gradleVersion = "6.6.1"
    distributionType = Wrapper.DistributionType.ALL
}
