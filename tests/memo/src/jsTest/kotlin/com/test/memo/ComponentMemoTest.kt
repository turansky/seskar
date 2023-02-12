package com.test.memo

import js.core.get
import kotlinx.coroutines.test.TestResult
import react.dom.test.runReactTest
import react.dom.test.simulateChange
import react.dom.test.simulateClick
import web.html.HTML.button
import web.html.HTML.div
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentMemoTest {
    @Test
    fun initial(): TestResult = runReactTest(CounterApp) { container ->
        val target = container.getElementsByTagName(div)[0]
        val button = container.getElementsByTagName(button)[0]

        assertEquals(1, target.dataCount, "Count #1.1")
        assertEquals(1, button.dataCount, "Count #1.2")

        target.simulateChange()
        assertEquals(2, target.dataCount, "Count #2.1")
        assertEquals(1, button.dataCount, "Count #2.2")

        target.simulateChange()
        assertEquals(3, target.dataCount, "Count #3.1")
        assertEquals(1, button.dataCount, "Count #3.2")

        button.simulateClick()
        assertEquals(3, target.dataCount, "Count #4.1")
        assertEquals(2, button.dataCount, "Count #4.2")

        button.simulateClick()
        assertEquals(3, target.dataCount, "Count #5.1")
        assertEquals(3, button.dataCount, "Count #5.2")
    }
}
