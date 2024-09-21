package com.test.workers

import web.events.iterator
import web.workers.messageEvent

suspend fun main() {
    val worker = createHelloWorker()

    for (event in worker.messageEvent) {
        console.log("Message:" + event.data)
    }
}
