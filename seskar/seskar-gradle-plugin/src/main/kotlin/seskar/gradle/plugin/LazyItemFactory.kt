package seskar.gradle.plugin

interface LazyItemFactory {
    fun create(
        source: String,
    ): LazyItem?
}
