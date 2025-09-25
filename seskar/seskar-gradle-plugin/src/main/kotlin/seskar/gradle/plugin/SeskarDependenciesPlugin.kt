package seskar.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.targets.js.ir.KotlinJsIrTarget

private val SESKAR_CORE_DEPENDENCY = "${KOTLIN_PLUGIN_ARTIFACT.groupId}:seskar-core:${KOTLIN_PLUGIN_ARTIFACT.version}"

private val SESKAR_IMPLEMENTATION = "seskarImplementation"

private val SESKAR_SUPPORTED_TARGETS = setOf(
    "metadata",
    "js",
    "wasmJs",
)

internal class SeskarDependenciesPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val seskarImplementation = createSeskarImplementationConfiguration(project)

        configureKotlinMultiplatform(project, seskarImplementation)
    }

    private fun createSeskarImplementationConfiguration(project: Project): Configuration {
        return project.configurations.create(SESKAR_IMPLEMENTATION) {
            description = "Seskar dependencies required during compilation and runtime Kotlin/JS."
            declarable()
            withDependencies {
                add(project.dependencies.create(SESKAR_CORE_DEPENDENCY))
            }
        }
    }

    /**
     * Automatically configure the Kotlin Multiplatform plugin, if it is applied to the same project.
     */
    private fun configureKotlinMultiplatform(
        project: Project,
        seskarImplementation: Configuration,
    ) {
        project.pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
            project.extensions.configure<KotlinMultiplatformExtension> {
                project.afterEvaluate {
                    val isCommon = targets.names.size > 1
                            && SESKAR_SUPPORTED_TARGETS.containsAll(targets.names)

                    if (isCommon) {
                        project.configurations.named("commonMainImplementation") {
                            extendsFrom(seskarImplementation)
                        }
                    } else {
                        targets.withType<KotlinJsIrTarget>().configureEach {
                            compilations.named(KotlinCompilation.MAIN_COMPILATION_NAME) {
                                project.configurations.named(implementationConfigurationName) {
                                    extendsFrom(seskarImplementation)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private companion object {
        /**
         * Mark this [Configuration] as one that should be used to declare dependencies in
         * [org.gradle.api.Project.dependencies] block.
         *
         * Declarable Configurations should be extended by 'resolvable' and 'consumable' Configurations.
         * They must not have [attributes][Configuration.attributes].
         */
        private fun Configuration.declarable() {
            isCanBeConsumed = false
            isCanBeConsumed = false
            @Suppress("UnstableApiUsage")
            isCanBeDeclared = true
        }
    }
}
