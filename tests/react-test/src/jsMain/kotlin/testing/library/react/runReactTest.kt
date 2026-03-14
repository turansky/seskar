package testing.library.react

import js.core.Void
import js.globals.globalThis
import js.promise.Promise
import js.test.runJsTest
import kotlinx.coroutines.CoroutineScope
import react.FC
import react.Props
import react.create
import web.html.HTMLElement

fun runReactTest(
    component: FC<Props>,
    testBody: suspend CoroutineScope.(container: HTMLElement) -> Unit,
): Promise<Void> {
    globalThis["IS_REACT_ACT_ENVIRONMENT"] = true

    return runJsTest {
        val result = render(component.create())
        val container = result.container as HTMLElement

        testBody(container)
    }
}
