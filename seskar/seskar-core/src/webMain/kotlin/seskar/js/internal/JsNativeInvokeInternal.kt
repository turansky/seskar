// TODO: remove annotation

@file:Suppress(
    "DEPRECATION",
)

@file:OptIn(
    ExperimentalWasmJsInterop::class,
)

package seskar.js.internal

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.nativeInvoke

typealias JsNativeInvokeInternal = nativeInvoke
