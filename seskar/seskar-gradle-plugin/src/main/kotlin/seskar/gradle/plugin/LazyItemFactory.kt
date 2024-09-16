package seskar.gradle.plugin

interface LazyItemFactory {
    fun create(
        export: String,
    ): LazyItem?
}
