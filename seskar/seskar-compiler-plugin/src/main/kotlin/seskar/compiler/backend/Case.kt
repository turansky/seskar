package seskar.compiler.backend

internal enum class Case {
    ORIGINAL,
    KEBAB,
    SNAKE,

    ;
}

internal fun String.toCase(case: Case): String =
    when (case) {
        Case.ORIGINAL -> this
        Case.KEBAB -> kebab()
        Case.SNAKE -> snake()
    }
