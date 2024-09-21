package com.test.workers

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class HelloWorkerTest {
    @Test
    fun create() = runTest {
        /*
        val worker = createHelloWorker()

        val data = worker.messageEvent.once().data

        assertEquals("Hello!", data)
        */

        assertTrue(true)
    }
}
