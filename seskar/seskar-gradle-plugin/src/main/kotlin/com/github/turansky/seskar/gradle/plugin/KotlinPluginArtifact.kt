package com.github.turansky.seskar.gradle.plugin

import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact

internal val KOTLIN_PLUGIN_ARTIFACT: SubpluginArtifact
    get() = SubpluginArtifact(
        groupId = "com.github.turansky.seskar",
        artifactId = "seskar-compiler-plugin",
        version = "0.0.4-SNAPSHOT"
    )
