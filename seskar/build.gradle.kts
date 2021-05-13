plugins {
    kotlin("js") apply false
    id("io.github.gradle-nexus.publish-plugin")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

tasks.wrapper {
    gradleVersion = "7.0.1"
    distributionType = Wrapper.DistributionType.ALL
}
