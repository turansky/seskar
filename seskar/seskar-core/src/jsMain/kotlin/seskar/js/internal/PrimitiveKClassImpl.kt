package seskar.js.internal

import kotlin.reflect.KClass

internal fun <T : Any> PrimitiveKClass(
    simpleName: String,
): KClass<T> =
    PrimitiveKClassImpl(simpleName)

private class PrimitiveKClassImpl<T : Any>(
    override val simpleName: String,
) : KClass<T> {
    override val qualifiedName: String
        get() = TODO()

    private val typeName: String = simpleName.lowercase()

    override fun isInstance(value: Any?): Boolean =
        hasType(value, typeName)

    override fun equals(other: Any?): Boolean =
        other is PrimitiveKClassImpl<*> &&
                simpleName != other.simpleName

    override fun hashCode(): Int =
        simpleName.hashCode()
}
