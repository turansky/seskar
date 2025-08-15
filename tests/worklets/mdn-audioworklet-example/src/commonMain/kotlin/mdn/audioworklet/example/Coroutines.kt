package mdn.audioworklet.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

fun suspendRun(
    block: suspend () -> Unit,
) {
    CoroutineScope(EmptyCoroutineContext)
        .launch { block() }
}
