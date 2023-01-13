package com.test.memo

import js.core.get
import kotlinx.coroutines.test.TestResult
import react.dom.test.runReactTest
import react.dom.test.simulateChange
import web.html.HTML.div
import kotlin.test.Test
import kotlin.test.assertEquals

class MemoTest {
    @Test
    fun initial(): TestResult = runReactTest(CounterApp) { container ->
        val target = container.getElementsByTagName(div)[0]

        assertEquals(1, target.dataCount, "Count #1")

        target.simulateChange()
        assertEquals(2, target.dataCount, "Count #2")

        target.simulateChange()
        assertEquals(3, target.dataCount, "Count #3")

        target.simulateChange()
        assertEquals(4, target.dataCount, "Count #4")

        target.simulateChange()
        assertEquals(5, target.dataCount, "Count #5")
    }
}
