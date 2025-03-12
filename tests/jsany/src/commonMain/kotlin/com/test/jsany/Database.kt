package com.test.jsany

import js.array.ReadonlyArray

external interface Database {
    val users: ReadonlyArray<User>
}
