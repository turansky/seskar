package js.union

import kotlin.test.Test
import kotlin.test.assertEquals

private class A {
    val a: Union2<String, Int>
        get() = Union2Impl(this, "aaa")
}

class Union2Test {

    @Test
    fun assignPropertyToString() {
        val a = A()
        a.a = "aaaValue"

        assertEquals(a.asDynamic()["aaa"], "aaaValue")
    }
}
