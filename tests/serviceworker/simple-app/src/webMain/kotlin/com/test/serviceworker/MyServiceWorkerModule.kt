package com.test.serviceworker

import web.serviceworker.ServiceWorkerModule
import web.timers.setInterval
import kotlin.time.Duration.Companion.milliseconds

val MyServiceWorkerModule = ServiceWorkerModule { self ->
    var count = 0

    println("Hello from Service Worker!!!")

    setInterval(100.milliseconds) {
        self.serviceWorker.postMessage("Hello ${++count}!")
    }
}
