package com.test.example

import js.core.jso
import kotlin.test.Test
import kotlin.test.assertEquals

class RecordTest {
    @Test
    fun testRead() {
        val record: MyRecord = jso<dynamic> {
            data = 42
        }

        assertEquals(42, record["data"])
    }

    @Test
    fun testWrite() {
        val record: MyRecord = jso()
        record["data"] = 42

        assertEquals(42, record.asDynamic().data)
    }
}
