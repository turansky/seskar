package seskar.gradle.plugin

import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact

internal val KOTLIN_PLUGIN_ARTIFACT: SubpluginArtifact
    get() = SubpluginArtifact(
        groupId = "io.github.turansky.seskar",
        artifactId = "seskar-compiler-plugin",
        version = "1.9.0-SNAPSHOT",
    )
