package seskar.compiler.env.backend

import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.name.FqName

private val ENV_VARIABLE = FqName("seskar.js.EnvVariable")

internal fun IrProperty.toEnvVariableName(): String? {
    if (!isExternal)
        return null

    if (!isTopLevel)
        return null

    getAnnotation(ENV_VARIABLE)
        ?: return null

    return "import.meta.env.VITE_${name.identifier}"
}
