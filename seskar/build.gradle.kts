plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.js-plain-objects") apply false
    id("io.github.gradle-nexus.publish-plugin")
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }

    // increased timeout
    transitionCheckOptions {
        maxRetries = 200
    }
}

tasks.wrapper {
    gradleVersion = "8.12"
}
