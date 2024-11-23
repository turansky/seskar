package com.test.workers

import js.module.JsModuleHandle
import web.workers.worker

@JsModuleHandle
val HelloWorker = worker { self ->
    self.postMessage("Hello!")
}
