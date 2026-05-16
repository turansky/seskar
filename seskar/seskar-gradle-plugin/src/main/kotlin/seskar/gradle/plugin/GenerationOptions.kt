package seskar.gradle.plugin

enum class GenerationOptions(
    val originalSuffix: String,
    val generatedSuffix: String,
) {
    LAZY_MODULE(
        originalSuffix = "__original__module.mjs",
        generatedSuffix = "__lazy__module.mjs",
    ),

    WORKER_FACTORY(
        originalSuffix = "__worker__factory.mjs",
        generatedSuffix = "__generated__worker.mjs",
    ),

    SERVICE_WORKER_MODULE(
        originalSuffix = "__serviceworker__module.mjs",
        generatedSuffix = "__generated__serviceworker__module.mjs",
    ),

    WORKLET_MODULE(
        originalSuffix = "__worklet__module.mjs",
        generatedSuffix = "__generated__worklet__module.mjs",
    ),

    ;
}
