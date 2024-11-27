package seskar.compiler.event.backend

import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.ir.util.getValueArgument
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

private val JS_EVENT = FqName("web.events.JsEvent")
private val TYPE = Name.identifier("type")

internal fun IrProperty.eventType(): String? {
    val event = getAnnotation(JS_EVENT)
        ?: return null

    val type = event.getValueArgument(TYPE) as IrConst
    return type.value as String
}
