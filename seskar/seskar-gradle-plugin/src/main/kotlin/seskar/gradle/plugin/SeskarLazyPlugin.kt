package seskar.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.create

private const val SESKAR_TASK_GROUP = "seskar"

internal class SeskarLazyPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        afterEvaluate {
            for (configuration in LazyConfiguration.ALL) {
                val syncTask = tasks.findByName(configuration.syncTask)
                    ?: continue

                val generateTask = tasks.create<Sync>(configuration.generateTask) {
                    group = SESKAR_TASK_GROUP

                    from(tasks.named(configuration.compileTask)) {
                        include("**/*__lazy__component.mjs")
                        includeEmptyDirs = false
                    }

                    into(temporaryDir)
                }

                syncTask.dependsOn(generateTask)
            }
        }
    }
}
