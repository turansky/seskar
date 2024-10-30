package com.test.workers

import kotlinx.coroutines.test.runTest
import web.events.once
import web.workers.Worker
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class HelloWorkerTest {
    @Test
    fun create() = runTest {
        val worker = createHelloWorker()

        assertIs<Worker>(worker)

        val data = worker.messageEvent.once().data

        assertEquals("Hello!", data)
    }
}
