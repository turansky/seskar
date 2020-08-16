allprojects {
    repositories {
        jcenter()
    }
}

tasks.wrapper {
    gradleVersion = "6.6"
    distributionType = Wrapper.DistributionType.ALL
}
