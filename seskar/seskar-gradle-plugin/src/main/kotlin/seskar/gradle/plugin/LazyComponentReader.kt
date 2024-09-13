package seskar.gradle.plugin

import java.io.FilterReader
import java.io.Reader
import java.io.StringReader

class LazyComponentReader(
    @Suppress("UNUSED_PARAMETER")
    reader: Reader,
) : FilterReader(StringReader("""import { lazy } from "react""""))
