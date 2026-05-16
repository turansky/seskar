package seskar.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.filter
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.tasks.IncrementalSyncTask
import seskar.gradle.plugin.GenerationOptions.*
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
                        options: GenerationOptions,
                        generatedFilter: KClass<out FilterReader>? = null,
                        originalFilter: KClass<out FilterReader>,
                    ) {
                        val originalSuffix = options.originalSuffix
                        val generatedSuffix = options.generatedSuffix

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
                        options = LAZY_MODULE,
                        originalFilter = LazyModuleReader::class,
                    )

                    // workers
                    addSource(
                        options = WORKER_FACTORY,
                        generatedFilter = GeneratedWorkerReader::class,
                        originalFilter = WorkerFactoryReader::class,
                    )

                    // service worker
                    addSource(
                        options = SERVICE_WORKER_MODULE,
                        generatedFilter = GeneratedWorkerReader::class,
                        originalFilter = ServiceWorkerModuleReader::class
                    )

                    // worklet modules
                    addSource(
                        options = WORKLET_MODULE,
                        generatedFilter = GeneratedWorkerReader::class,
                        originalFilter = WorkletModuleReader::class,
                    )

                    into(temporaryDir)
                }

                tasks.named<IncrementalSyncTask>(configuration.syncTask) {
                    excludeOriginalSources()

                    from.from(generateTask)
                }
            }
        }
    }
}
