package com.test.union

import kotlin.test.Test
import kotlin.test.assertEquals

class MyRequestTypeTest {
    @Test
    fun `test get`() {
        assertEquals<Any>("GET".toJsString(), MyRequestType.GET)
    }
}
