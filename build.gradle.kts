allprojects {
    repositories {
        jcenter()
    }
}

tasks.wrapper {
    gradleVersion = "7.0"
    distributionType = Wrapper.DistributionType.ALL
}
