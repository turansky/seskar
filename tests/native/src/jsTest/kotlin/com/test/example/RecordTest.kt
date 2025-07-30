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

    @Test
    fun testFalsePositiveRead() {
        val record: MyCustomRecord = object : MyCustomRecord {
            private val data = mutableMapOf("key" to 42)

            override operator fun get(
                key: String,
                default: () -> Int,
            ): Int {
                return data[key]
                    ?: default()
            }
        }

        assertEquals(27, record["value", { 27 }])
        assertEquals(42, record["key", { 27 }])
    }
}
