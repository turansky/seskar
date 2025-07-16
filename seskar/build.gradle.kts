plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.js-plain-objects") apply false
    id("io.github.gradle-nexus.publish-plugin")
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
        }
    }
}

tasks.wrapper {
    gradleVersion = "8.14.2"
}
