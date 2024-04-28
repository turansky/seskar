package js.union

class Union2Impl<in A : Any, in B : Any>(
    private val target: Any,
    private val propertyName: String,
) : Union2<A, B> {
    override fun assign(
        value: A,
    ) {
        target.asDynamic()[propertyName] = value
    }

    override fun assign(
        value: B,
    ) {
        target.asDynamic()[propertyName] = value
    }
}
