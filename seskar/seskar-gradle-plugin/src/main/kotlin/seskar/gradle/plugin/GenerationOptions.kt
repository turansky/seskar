package seskar.gradle.plugin

object Modules {
    const val LAZY_MODULE_SUFFIX = "__lazy__module.mjs"
    const val ORIGINAL_MODULE_SUFFIX = "__original__module.mjs"
}

object ServiceWorkers {
    const val SERVICE_WORKER_MODULE_SUFFIX = "__serviceworker__module.mjs"
    const val GENERATED_SERVICE_WORKER_MODULE_SUFFIX = "__generated__serviceworker__module.mjs"
}

object Workers {
    const val WORKER_FACTORY_SUFFIX = "__worker__factory.mjs"
    const val GENERATED_WORKER_SUFFIX = "__generated__worker.mjs"
}

object Worklets {
    const val WORKLET_MODULE_SUFFIX = "__worklet__module.mjs"
    const val GENERATED_WORKLET_MODULE_SUFFIX = "__generated__worklet__module.mjs"
}
