allprojects {
    repositories {
        jcenter()
    }
}

tasks.wrapper {
    gradleVersion = "6.8.3"
    distributionType = Wrapper.DistributionType.ALL
}
