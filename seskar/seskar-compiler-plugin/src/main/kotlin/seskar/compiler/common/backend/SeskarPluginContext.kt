package seskar.compiler.common.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrBuiltIns
import org.jetbrains.kotlin.ir.declarations.IrFactory
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId

internal class SeskarPluginContext(
    private val context: IrPluginContext,
) {
    val irBuiltIns: IrBuiltIns
        get() = context.irBuiltIns

    val irFactory: IrFactory
        get() = context.irFactory

    fun findClass(
        classId: ClassId,
    ): IrClassSymbol? =
        context.finderForBuiltins()
            .findClass(classId)

    fun findProperty(
        callableId: CallableId,
    ): IrPropertySymbol =
        context.finderForBuiltins()
            .findProperties(callableId)
            .single()

    fun findFunction(
        callableId: CallableId,
    ): IrSimpleFunctionSymbol =
        findFunctions(callableId).single()

    fun findFunctions(
        callableId: CallableId,
    ): Collection<IrSimpleFunctionSymbol> =
        context.finderForBuiltins()
            .findFunctions(callableId)

    fun findConstructor(
        classId: ClassId,
    ): IrConstructorSymbol =
        findConstructors(classId).single()

    fun findConstructors(
        classId: ClassId,
    ): Collection<IrConstructorSymbol> =
        context.finderForBuiltins()
            .findConstructors(classId)
}