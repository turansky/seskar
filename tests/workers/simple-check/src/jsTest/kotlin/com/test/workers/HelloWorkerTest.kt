package com.test.workers

import kotlinx.coroutines.test.runTest
import web.events.once
import web.workers.Worker
import web.workers.WorkerOptions
import web.workers.WorkerType
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class HelloWorkerTest {
    // TEMP
    @Ignore
    @Test
    fun create() = runTest {
        val worker = Worker(HelloWorker, WorkerOptions(type = WorkerType.module))

        val data = worker.messageEvent.once().data

        assertEquals("Hello!", data)
    }
}
