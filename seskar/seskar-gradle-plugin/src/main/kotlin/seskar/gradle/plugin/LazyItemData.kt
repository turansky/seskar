package seskar.gradle.plugin

import seskar.gradle.plugin.Modules.ORIGINAL_MODULE_SUFFIX

data class LazyItemData(
    val name: String,
    val originalFilePath: String,
    val type: LazyItemType,
    val export: String,
)

fun LazyItemData(
    export: String,
): LazyItemData {
    val fileName = export
        .removePrefix("get_")
        .substringBefore("__react__component")

    return LazyItemData(
        name = fileName,
        originalFilePath = "./$fileName$ORIGINAL_MODULE_SUFFIX",
        type = LazyItemType.LAZY_REACT_COMPONENT,
        export = export,
    )
}
