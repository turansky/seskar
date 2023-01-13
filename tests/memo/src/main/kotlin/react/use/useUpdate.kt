package react.use

import react.useCallback
import react.useState

// TODO: remove after wrappers update
fun useUpdate(): () -> Unit {
    val (_, setTrigger) = useState(0)
    return useCallback {
        setTrigger { it + 1 }
    }
}
