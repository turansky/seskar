package seskar.gradle.plugin

fun interface FileTransformer {
    fun transform(
        content: String,
    ): String
}
