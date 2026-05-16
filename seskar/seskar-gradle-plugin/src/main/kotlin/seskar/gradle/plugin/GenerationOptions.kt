package seskar.gradle.plugin

enum class GenerationOptions(
    val originalSuffix: String,
    val generatedSuffix: String,
) {
    LAZY_MODULE(
        "__lazy__module.mjs",
        "__original__module.mjs",
    ),

    SERVICE_WORKER_MODULE(
        "__serviceworker__module.mjs",
        "__generated__serviceworker__module.mjs",
    ),

    WORKER_FACTORY(
        "__worker__factory.mjs",
        "__generated__worker.mjs",
    ),

    WORKLET_MODULE(
        "__worklet__module.mjs",
        "__generated__worklet__module.mjs",
    ),

    ;
}
