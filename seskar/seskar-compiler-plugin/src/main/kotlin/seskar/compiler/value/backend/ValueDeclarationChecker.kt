package seskar.compiler.value.backend

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory0
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.resolve.checkers.DeclarationChecker
import org.jetbrains.kotlin.resolve.checkers.DeclarationCheckerContext
import seskar.compiler.value.diagnostic.ValueErrors

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
        if (!descriptor.annotations.hasAnnotation(JS_VALUE)) return

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

        if (value.type.isMarkedNullable) {
            reportError(ValueErrors.NULLABLE_JS_VALUE)
        }
    }
}
