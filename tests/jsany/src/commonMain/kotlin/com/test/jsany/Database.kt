package com.test.jsany

import com.test.jsany.UserData.Address
import com.test.jsany.UserData.Hobby
import js.array.ReadonlyArray

external interface Database {
    val users: ReadonlyArray<User>
    val addresses: ReadonlyArray<Address>
    val hobbies: ReadonlyArray<Hobby>
}
