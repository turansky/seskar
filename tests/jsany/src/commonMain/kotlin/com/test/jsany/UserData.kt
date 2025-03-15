package com.test.jsany

external interface UserData {

    interface Address {
        val street: String
        val suite: String
        val city: String
    }

    interface Hobby {
        val id: String
        val name: String
    }
}
