package seskar.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.filter
import org.gradle.kotlin.dsl.named
import org.jetbrains.kotlin.gradle.tasks.IncrementalSyncTask

private const val SESKAR_TASK_GROUP = "seskar"

internal class SeskarLazyPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        afterEvaluate {
            for (configuration in LazyConfiguration.ALL) {
                tasks.findByName(configuration.syncTask)
                    ?: continue

                val compileTask = tasks.named(configuration.compileTask)

                val generateTask = tasks.create<Sync>(configuration.generateTask) {
                    group = SESKAR_TASK_GROUP

                    from(compileTask) {
                        include("**/*__lazy__component.mjs")
                        rename { it.replace("__lazy__", "__original__") }

                        includeEmptyDirs = false
                    }

                    from(compileTask) {
                        include("**/*__lazy__component.mjs")

                        filter(LazyComponentReader::class)

                        includeEmptyDirs = false
                    }

                    into(temporaryDir)
                }

                tasks.named<IncrementalSyncTask>(configuration.syncTask) {
                    from.from(generateTask.destinationDir)

                    dependsOn(generateTask)
                }
            }
        }
    }
}
