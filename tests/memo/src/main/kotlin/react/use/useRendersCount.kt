package react.use

import react.useRefValue

// TODO: remove after wrappers update
fun useRendersCount(): Int {
    var count by useRefValue(0)
    count++
    return count
}
