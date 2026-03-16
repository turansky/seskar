package testing.library.react

import js.core.Void
import js.promise.Promise
import js.test.runJsTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.job
import react.FC
import react.Props
import react.create
import web.html.HTMLElement

fun runReactTest(
    component: FC<Props>,
    testBody: suspend CoroutineScope.(container: HTMLElement) -> Unit,
): Promise<Void> =
    runJsTest {
        coroutineContext.job.invokeOnCompletion {
            cleanup()
        }
        
        val result = render(component.create())
        val container = result.container as HTMLElement

        testBody(container)
    }
