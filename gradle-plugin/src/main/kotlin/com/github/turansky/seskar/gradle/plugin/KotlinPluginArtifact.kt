package com.github.turansky.seskar.gradle.plugin

import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact

internal val KOTLIN_PLUGIN_ARTIFACT: SubpluginArtifact
    get() = SubpluginArtifact(
        groupId = "gradle.plugin.com.github.turansky.seskar",
        artifactId = "gradle-plugin",
        version = "0.0.2-SNAPSHOT"
    )
