package react.dom.test

import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import react.VFC
import web.dom.document
import web.html.HTML.div
import web.html.HTMLElement

fun runReactTest(
    testBody: suspend TestScope.(container: HTMLElement) -> Unit,
): TestResult =
    runTest {
        val container = document.createElement(div)
        document.body.appendChild(container)

        testBody(container)
    }

fun runReactTest(
    component: VFC,
    testBody: suspend TestScope.(container: HTMLElement) -> Unit,
): TestResult =
    runReactTest { container ->
        val root = createRoot(container, component)

        testBody(container)

        unmount(root)
    }
