package seskar.gradle.plugin

interface LazyItemFactory {
    fun create(
        data: LazyItemData,
    ): LazyItem?
}
