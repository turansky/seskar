package seskar.compiler.union.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.ir.addDispatchReceiver
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.builders.declarations.addGetter
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl
import org.jetbrains.kotlin.ir.symbols.impl.IrClassSymbolImpl
import org.jetbrains.kotlin.ir.symbols.impl.IrConstructorSymbolImpl
import org.jetbrains.kotlin.ir.symbols.impl.IrPropertySymbolImpl
import org.jetbrains.kotlin.ir.util.companionObject
import org.jetbrains.kotlin.ir.util.isTopLevelDeclaration
import org.jetbrains.kotlin.ir.util.parentAsClass
import org.jetbrains.kotlin.ir.visitors.IrElementTransformer
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.name.SpecialNames

private object SYNTHETIC_UNION_COMPANION : IrDeclarationOriginImpl("SYNTHETIC_UNION_COMPANION", isSynthetic = true)
private object SYNTHETIC_UNION_PROPERTY : IrDeclarationOriginImpl("SYNTHETIC_UNION_PROPERTY", isSynthetic = true)

internal class UnionTransformer(
    private val context: IrPluginContext,
) : IrElementTransformer<ValueMode?> {
    override fun visitClass(
        declaration: IrClass,
        data: ValueMode?,
    ): IrStatement {
        val value = declaration.value()

        if (value != null && declaration.isTopLevelDeclaration && declaration.kind == ClassKind.OBJECT) {
            declaration.annotations += JsName(context, declaration, value.toJsName())
            return declaration
        }

        val mode = when {
            declaration.isJsUnion()
            -> ValueMode.ROOT

            data == ValueMode.ROOT && declaration.isCompanion
            -> ValueMode.COMPANION

            /*
            data == ValueMode.ROOT && declaration.isNonCompanionObject
            -> return visitUnionEntry(declaration)
            */

            else -> null
        }

        if (mode == ValueMode.ROOT) {
            declaration.annotations += JsName(context, declaration, "0")
            /*
            checkCompanionObject(declaration)
            */
        }

        return super.visitClass(declaration, mode)
    }

    private fun checkCompanionObject(
        union: IrClass,
    ) {
        if (union.companionObject() != null)
            return

        val companion = context.irFactory.createClass(
            startOffset = union.startOffset,
            endOffset = union.endOffset,
            origin = SYNTHETIC_UNION_COMPANION,
            // constant?
            name = Name.identifier("Companion"),
            visibility = union.visibility,
            symbol = IrClassSymbolImpl(),
            kind = ClassKind.OBJECT,
            modality = Modality.FINAL,
            isExternal = true,
            isCompanion = true,
        )

        // required?
        val constructor = context.irFactory.createConstructor(
            startOffset = union.startOffset,
            endOffset = union.endOffset,
            origin = IrDeclarationOrigin.DEFINED,
            name = SpecialNames.INIT,
            visibility = DescriptorVisibilities.PRIVATE,
            isInline = false,
            isExpect = false,
            returnType = context.irBuiltIns.unitType,
            symbol = IrConstructorSymbolImpl(),
            isPrimary = true,
            // required?
            isExternal = true,
        )

        companion.declarations.add(constructor)
        constructor.parent = companion

        union.declarations.add(companion)
        companion.parent = union
    }

    private fun visitUnionEntry(
        unionEntry: IrClass,
    ): IrClass {
        unionEntry.kind = ClassKind.INTERFACE
        unionEntry.declarations.clear()

        val companion = unionEntry.parentAsClass.companionObject()
            ?: error("No companion!")

        val property = context.irFactory.createProperty(
            startOffset = unionEntry.startOffset,
            endOffset = unionEntry.endOffset,
            origin = SYNTHETIC_UNION_PROPERTY,
            name = unionEntry.name,
            visibility = unionEntry.visibility,
            modality = unionEntry.modality,
            symbol = IrPropertySymbolImpl(),
            isVar = false,
            isConst = false,
            isLateinit = false,
            isDelegated = false,
        )

        property.parent = companion
        companion.declarations.add(property)

        addPropertyGetter(property, unionEntry.value(useDefaultValue = true)!!)

        return unionEntry
    }

    override fun visitProperty(
        declaration: IrProperty,
        data: ValueMode?,
    ): IrStatement {
        val value = declaration.value(useDefaultValue = (data == ValueMode.COMPANION))
            ?: return declaration

        addPropertyGetter(declaration, value)

        return super.visitProperty(declaration, data)
    }

    private fun addPropertyGetter(
        declaration: IrProperty,
        value: Value,
    ) {
        val getter = declaration.addGetter {
            isInline = true
            returnType = context.irBuiltIns.stringType
        }

        getter.addDispatchReceiver {
            type = context.irBuiltIns.nothingNType
        }

        getter.body = context.irFactory.createBlockBody(
            startOffset = declaration.startOffset,
            endOffset = declaration.endOffset,
            statements = listOf(
                IrReturnImpl(
                    startOffset = declaration.startOffset,
                    endOffset = declaration.endOffset,
                    type = context.irBuiltIns.nothingNType,
                    returnTargetSymbol = getter.symbol,
                    value = valueConstant(declaration, value),
                )
            )
        )
    }

    private fun valueConstant(
        declaration: IrDeclarationWithName,
        value: Value,
    ): IrExpression {
        return when (value) {
            is IntValue ->
                IrConstImpl.int(
                    startOffset = declaration.startOffset,
                    endOffset = declaration.endOffset,
                    type = context.irBuiltIns.intType,
                    value = value.value,
                )

            is StringValue ->
                IrConstImpl.string(
                    startOffset = declaration.startOffset,
                    endOffset = declaration.endOffset,
                    type = context.irBuiltIns.stringType,
                    value = value.value,
                )
        }
    }
}
