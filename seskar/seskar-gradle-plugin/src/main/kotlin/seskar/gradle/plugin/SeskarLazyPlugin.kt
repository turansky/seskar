package seskar.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.create

private const val SESKAR_TASK_GROUP = "seskar"

internal class SeskarLazyPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        plugins.withId("io.github.turansky.kfc.application") {
            for (configuration in LazyConfiguration.ALL) {
                val generateTask = tasks.create<Sync>(configuration.generateTask) {
                    group = SESKAR_TASK_GROUP

                    from(tasks.named(configuration.compileTask)) {
                        include("**/*__lazy__component.mjs")
                        includeEmptyDirs = false
                    }

                    into(temporaryDir)
                }

                tasks.named(configuration.syncTask) {
                    dependsOn(generateTask)
                }
            }
        }
    }
}
