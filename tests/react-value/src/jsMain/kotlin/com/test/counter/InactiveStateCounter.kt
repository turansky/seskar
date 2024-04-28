package com.test.counter

import react.FC

val InactiveStateCounter = FC {
    StateCounter {
        active = false
    }
}
