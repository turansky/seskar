package seskar.gradle.plugin

fun replaceBlock(
    content: String,
    blockStart: String,
    blockEnd: String,
    transform: (String) -> String,
): String {
    val blockContent = content
        .substringAfter(blockStart, "")
        .substringBefore(blockEnd, "")

    if (blockContent.isEmpty())
        return content

    return content.replaceFirst(
        oldValue = blockStart + blockContent + blockEnd,
        newValue = transform(blockContent),
    )
}
