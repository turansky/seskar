package seskar.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

private const val JS_MAIN_IMPLEMENTATION: String = "jsMainImplementation"

private val SESKAR_REACT: String
    get() = sequenceOf(
        KOTLIN_PLUGIN_ARTIFACT.groupId,
        "seskar-react",
        KOTLIN_PLUGIN_ARTIFACT.version,
    ).joinToString(":")

internal class SeskarReactPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        dependencies {
            JS_MAIN_IMPLEMENTATION(SESKAR_REACT)
        }
    }
}
