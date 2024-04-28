package seskar.compiler.value.backend

internal sealed interface Value

@JvmInline
internal value class IntValue(
    val value: Int,
) : Value

@JvmInline
internal value class StringValue(
    val value: String,
) : Value

internal fun Value.toJsName(): String =
    when (this) {
        is IntValue -> "$value"
        is StringValue -> "'$value'"
    }
