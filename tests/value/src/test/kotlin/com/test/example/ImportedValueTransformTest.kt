package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class ImportedValueTransformTest {

    @Test
    fun bananas_local() {
        val bananas = Bananas(11)

        val array = useArray(bananas)
        assertEquals(11, array[0])
    }

    @Test
    fun bananas_imported() {
        val bananas = Bananas(13)

        val array = useImportedArray(bananas)
        assertEquals(13, array[0])
    }

    @Test
    fun age_imported() {
        val age = Age(17)

        val array = useImportedArray(age)
        assertEquals(17, array[0])
    }
}
