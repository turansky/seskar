@file:JsModule("my-cancellable-response-library")

package com.test.example

import js.array.ReadonlyArray
import js.promise.Promise
import seskar.js.JsAsync
import web.abort.Abortable

@JsName("getCancellableResponseOnlyWithOptions")
external fun getCancellableResponseOnlyWithOptionsAsync(
    options: Abortable = definedExternally,
): Promise<ReadonlyArray<Any?>>

@JsAsync
external suspend fun getCancellableResponseOnlyWithOptions(
    options: Abortable = definedExternally,
): ReadonlyArray<Any?>

@JsName("getCancellableResponseWithOptions")
external fun getCancellableResponseWithOptionsAsync(
    a: String = definedExternally,
    b: Int = definedExternally,
    c: Boolean = definedExternally,
    options: Abortable = definedExternally,
): Promise<ReadonlyArray<Any?>>

@JsAsync
external suspend fun getCancellableResponseWithOptions(
    a: String = definedExternally,
    b: Int = definedExternally,
    c: Boolean = definedExternally,
    options: Abortable = definedExternally,
): ReadonlyArray<Any?>

external interface CancellableOptions1 : Abortable
external interface CancellableOptions2 : CancellableOptions1
external interface CancellableOptions3 : CancellableOptions2

@JsName("getCancellableResponseOnlyWithParentOptions")
external fun getCancellableResponseOnlyWithParentOptionsAsync(
    options: CancellableOptions3 = definedExternally,
): Promise<ReadonlyArray<Any?>>

@JsAsync
external suspend fun getCancellableResponseOnlyWithParentOptions(
    options: CancellableOptions3 = definedExternally,
): ReadonlyArray<Any?>

@JsName("getCancellableResponseWithParentOptions")
external fun getCancellableResponseWithParentOptionsAsync(
    a: String = definedExternally,
    b: Int = definedExternally,
    c: Boolean = definedExternally,
    options: Abortable = definedExternally,
): Promise<ReadonlyArray<Any?>>

@JsAsync
external suspend fun getCancellableResponseWithParentOptions(
    a: String = definedExternally,
    b: Int = definedExternally,
    c: Boolean = definedExternally,
    options: Abortable = definedExternally,
): ReadonlyArray<Any?>
