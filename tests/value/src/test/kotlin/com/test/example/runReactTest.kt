package com.test.example

import kotlinx.coroutines.test.runTest
import react.VFC
import web.dom.document
import web.html.HTML.div
import web.html.HTMLElement

fun runReactTest(
    testBody: suspend (container: HTMLElement) -> Unit,
) {
    runTest {
        val container = document.createElement(div)
        document.body.appendChild(container)

        testBody(container)
    }
}

fun runReactTest(
    component: VFC,
    testBody: suspend (container: HTMLElement) -> Unit,
) {
    runReactTest { container ->
        val root = createRoot(container, component)

        testBody(container)

        act {
            root.unmount()
        }
    }
}
