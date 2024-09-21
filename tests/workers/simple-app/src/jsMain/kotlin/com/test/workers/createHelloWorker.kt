package com.test.workers

import web.workers.WorkerFactory

val createHelloWorker = WorkerFactory { self ->
    self.postMessage("Hello!")
}
