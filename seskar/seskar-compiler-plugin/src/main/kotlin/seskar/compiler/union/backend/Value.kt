package seskar.compiler.union.backend

sealed interface Value

@JvmInline
value class IntValue(
    val value: Int,
) : Value

@JvmInline
value class StringValue(
    val value: String,
) : Value
