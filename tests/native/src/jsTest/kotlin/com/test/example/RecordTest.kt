package com.test.example

import js.objects.unsafeJso
import kotlin.test.Test
import kotlin.test.assertEquals

class RecordTest {
    @Test
    fun testRead() {
        val record: MyRecord = unsafeJso<dynamic> {
            data = 42
        }

        assertEquals(42, record["data"])
    }

    @Test
    fun testWrite() {
        val record: MyRecord = unsafeJso()
        record["data"] = 42

        assertEquals(42, record.asDynamic().data)
    }
}
