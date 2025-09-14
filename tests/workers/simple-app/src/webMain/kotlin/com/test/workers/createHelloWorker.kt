package com.test.workers

import web.timers.setInterval
import web.workers.WorkerFactory
import kotlin.time.Duration.Companion.milliseconds

val createHelloWorker = WorkerFactory { self ->
    var count = 0

    println("Hello from Worker!!!")

    setInterval(100.milliseconds) {
        self.postMessage("Hello ${++count}!")
    }
}
