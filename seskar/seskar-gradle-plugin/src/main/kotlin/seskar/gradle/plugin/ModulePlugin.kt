package seskar.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.filter
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.tasks.IncrementalSyncTask
import seskar.gradle.plugin.Modules.LAZY_MODULE_SUFFIX
import seskar.gradle.plugin.Modules.ORIGINAL_MODULE_SUFFIX
import seskar.gradle.plugin.ServiceWorkers.GENERATED_SERVICE_WORKER_MODULE_SUFFIX
import seskar.gradle.plugin.ServiceWorkers.SERVICE_WORKER_MODULE_SUFFIX
import seskar.gradle.plugin.Workers.GENERATED_WORKER_SUFFIX
import seskar.gradle.plugin.Workers.WORKER_FACTORY_SUFFIX
import seskar.gradle.plugin.Worklets.GENERATED_WORKLET_MODULE_SUFFIX
import seskar.gradle.plugin.Worklets.WORKLET_MODULE_SUFFIX

internal class ModulePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        afterEvaluate {
            for (configuration in LazyConfiguration.ALL) {
                tasks.findByName(configuration.syncTask)
                    ?: continue

                val compileTask = tasks.named(configuration.compileTask)

                val generateTask = tasks.register<Sync>(configuration.generateTask) {
                    group = Seskar.TASK_GROUP

                    // lazy
                    from(compileTask) {
                        include("**/*$LAZY_MODULE_SUFFIX")
                        rename { it.removeSuffix(LAZY_MODULE_SUFFIX) + ORIGINAL_MODULE_SUFFIX }

                        includeEmptyDirs = false
                    }

                    from(compileTask) {
                        include("**/*$LAZY_MODULE_SUFFIX")

                        filter(LazyModuleReader::class)

                        includeEmptyDirs = false
                    }

                    // workers
                    from(compileTask) {
                        include("**/*$WORKER_FACTORY_SUFFIX")
                        rename { it.removeSuffix(WORKER_FACTORY_SUFFIX) + GENERATED_WORKER_SUFFIX }

                        filter(GeneratedWorkerReader::class)

                        includeEmptyDirs = false
                    }

                    from(compileTask) {
                        include("**/*$WORKER_FACTORY_SUFFIX")

                        filter(WorkerFactoryReader::class)

                        includeEmptyDirs = false
                    }

                    // service worker
                    from(compileTask) {
                        include("**/*$SERVICE_WORKER_MODULE_SUFFIX")
                        rename { it.removeSuffix(SERVICE_WORKER_MODULE_SUFFIX) + GENERATED_SERVICE_WORKER_MODULE_SUFFIX }

                        filter(GeneratedWorkerReader::class)

                        includeEmptyDirs = false
                    }

                    from(compileTask) {
                        include("**/*$SERVICE_WORKER_MODULE_SUFFIX")

                        filter(ServiceWorkerModuleReader::class)

                        includeEmptyDirs = false
                    }

                    // worklet modules
                    from(compileTask) {
                        include("**/*$WORKLET_MODULE_SUFFIX")
                        rename { it.removeSuffix(WORKLET_MODULE_SUFFIX) + GENERATED_WORKLET_MODULE_SUFFIX }

                        filter(GeneratedWorkerReader::class)

                        includeEmptyDirs = false
                    }

                    from(compileTask) {
                        include("**/*$WORKLET_MODULE_SUFFIX")

                        filter(WorkletModuleReader::class)

                        includeEmptyDirs = false
                    }

                    into(temporaryDir)
                }

                tasks.named<IncrementalSyncTask>(configuration.syncTask) {
                    from.from(generateTask.get().destinationDir)

                    duplicatesStrategy = DuplicatesStrategy.INCLUDE

                    dependsOn(generateTask)
                }
            }
        }
    }
}
