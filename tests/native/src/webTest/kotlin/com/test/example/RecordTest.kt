package com.test.example

import js.numbers.n
import js.objects.unsafeJso
import js.reflect.Reflect
import kotlin.test.Test
import kotlin.test.assertEquals

class RecordTest {
    @Test
    fun testRead() {
        val record: MyRecord = unsafeJso {
            Reflect.set(this, "data", 42.n)
        }

        assertEquals(42.n, record["data"])
    }

    @Test
    fun testWrite() {
        val record: MyRecord = unsafeJso()
        record["data"] = 42.n

        assertEquals(42.n, Reflect.get(record, "data"))
    }
}
