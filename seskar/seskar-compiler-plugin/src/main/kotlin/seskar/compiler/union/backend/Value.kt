package seskar.compiler.union.backend

internal sealed interface Value

@JvmInline
internal value class IntValue(
    val value: Int,
) : Value

@JvmInline
internal value class StringValue(
    val value: String,
) : Value
