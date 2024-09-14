package seskar.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.filter
import org.gradle.kotlin.dsl.named
import org.jetbrains.kotlin.gradle.tasks.IncrementalSyncTask
import seskar.gradle.plugin.Components.LAZY_COMPONENT_SUFFIX
import seskar.gradle.plugin.Components.ORIGINAL_COMPONENT_SUFFIX

private const val SESKAR_TASK_GROUP = "seskar"

internal class ReactLazyComponentPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        afterEvaluate {
            for (configuration in LazyConfiguration.ALL) {
                tasks.findByName(configuration.syncTask)
                    ?: continue

                val compileTask = tasks.named(configuration.compileTask)

                val generateTask = tasks.create<Sync>(configuration.generateTask) {
                    group = SESKAR_TASK_GROUP

                    from(compileTask) {
                        include("**/*$LAZY_COMPONENT_SUFFIX")
                        rename { it.removeSuffix(LAZY_COMPONENT_SUFFIX) + ORIGINAL_COMPONENT_SUFFIX }

                        includeEmptyDirs = false
                    }

                    from(compileTask) {
                        include("**/*$LAZY_COMPONENT_SUFFIX")

                        filter(ReactLazyComponentReader::class)

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
