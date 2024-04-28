package seskar.compiler.react.value.backend

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory0
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.resolve.checkers.DeclarationChecker
import org.jetbrains.kotlin.resolve.checkers.DeclarationCheckerContext
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.builtIns
import seskar.compiler.react.value.diagnostic.ValueErrors

internal object ValueDeclarationChecker : DeclarationChecker {
    override fun check(
        declaration: KtDeclaration,
        descriptor: DeclarationDescriptor,
        context: DeclarationCheckerContext,
    ) {
        if (declaration !is KtClassOrObject) return
        if (descriptor !is ClassDescriptor) return
        if (descriptor.kind != ClassKind.CLASS) return
        if (!descriptor.isValue) return
        // if (!descriptor.annotations.hasAnnotation(JS_VALUE)) return

        descriptor.check {
            context.trace.report(it.on(declaration))
        }
    }

    private fun ClassDescriptor.check(
        reportError: (DiagnosticFactory0<KtClassOrObject>) -> Unit,
    ) {
        val constructor = constructors.singleOrNull()
            ?: return // TODO: throw error

        val value = constructor.valueParameters.singleOrNull()
            ?: return // TODO: throw error

        val type = value.type

        if (type.isMarkedNullable) {
            reportError(ValueErrors.NULLABLE_JS_VALUE)
        }

        if (!validValueType(type)) {
            reportError(ValueErrors.INVALID_JS_VALUE_TYPE)
        }
    }

    private fun validValueType(
        type: KotlinType,
    ): Boolean {
        val builtIns = type.builtIns

        return when (type) {
            builtIns.stringType,

            builtIns.intType,
            builtIns.doubleType,
            builtIns.longType,
            -> true

            else -> false
        }
    }
}
