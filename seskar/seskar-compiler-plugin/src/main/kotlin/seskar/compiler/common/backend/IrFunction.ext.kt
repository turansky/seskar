package seskar.compiler.common.backend

import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrParameterKind
import org.jetbrains.kotlin.ir.declarations.IrValueParameter

internal fun IrFunction.getValueParameters(): List<IrValueParameter> =
    parameters.filter { it.kind == IrParameterKind.Regular || it.kind == IrParameterKind.Context }
