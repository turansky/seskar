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
import java.io.FilterReader
import kotlin.reflect.KClass

internal class ModulePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        afterEvaluate {
            for (configuration in LazyConfiguration.ALL) {
                tasks.findByName(configuration.syncTask)
                    ?: continue

                val compileTask = tasks.named(configuration.compileTask)

                val generateTask = tasks.register<Sync>(configuration.generateTask) {
                    group = Seskar.TASK_GROUP

                    fun addSource(
                        originalSuffix: String,
                        generatedSuffix: String,
                        generatedFilter: KClass<out FilterReader>? = null,
                        originalFilter: KClass<out FilterReader>,
                    ) {
                        from(compileTask) {
                            include("**/*$originalSuffix")
                            rename { it.removeSuffix(originalSuffix) + generatedSuffix }

                            if (generatedFilter != null) {
                                filter(generatedFilter)
                            }

                            includeEmptyDirs = false
                        }

                        from(compileTask) {
                            include("**/*$originalSuffix")

                            filter(originalFilter)

                            includeEmptyDirs = false
                        }
                    }

                    // lazy
                    addSource(
                        originalSuffix = LAZY_MODULE_SUFFIX,
                        generatedSuffix = ORIGINAL_MODULE_SUFFIX,
                        originalFilter = LazyModuleReader::class,
                    )

                    // workers
                    addSource(
                        originalSuffix = WORKER_FACTORY_SUFFIX,
                        generatedSuffix = GENERATED_WORKER_SUFFIX,
                        generatedFilter = GeneratedWorkerReader::class,
                        originalFilter = WorkerFactoryReader::class,
                    )

                    // service worker
                    addSource(
                        originalSuffix = SERVICE_WORKER_MODULE_SUFFIX,
                        generatedSuffix = GENERATED_SERVICE_WORKER_MODULE_SUFFIX,
                        generatedFilter = GeneratedWorkerReader::class,
                        originalFilter = ServiceWorkerModuleReader::class
                    )

                    // worklet modules
                    addSource(
                        originalSuffix = WORKLET_MODULE_SUFFIX,
                        generatedSuffix = GENERATED_WORKLET_MODULE_SUFFIX,
                        generatedFilter = GeneratedWorkerReader::class,
                        originalFilter = WorkletModuleReader::class,
                    )

                    into(temporaryDir)
                }

                tasks.named<IncrementalSyncTask>(configuration.syncTask) {
                    from.from(generateTask)

                    duplicatesStrategy = DuplicatesStrategy.INCLUDE
                }
            }
        }
    }
}
