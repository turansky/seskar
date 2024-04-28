package js.union

@AssignmentOverload
sealed interface Union2<in A : Any, in B : Any> {
    fun assign(
        value: A,
    )

    fun assign(
        value: B,
    )
}
