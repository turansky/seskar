package seskar.compiler.props.diagnostic

import org.jetbrains.kotlin.diagnostics.DiagnosticFactory0
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.diagnostics.Severity.ERROR
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtElement
import kotlin.reflect.KClass

private fun <T : KtElement> errorDiagnosticFactory(): DiagnosticFactory0<T> =
    DiagnosticFactory0.create(ERROR)

private fun initialize(klass: KClass<*>) {
    Errors.Initializer.initializeFactoryNamesAndDefaultErrorMessages(klass.java, PropsMessagesExtension)
}

internal object PropsErrors {
    @JvmField
    val NON_EXTERNAL_PROPS: DiagnosticFactory0<KtClassOrObject> =
        errorDiagnosticFactory()

    init {
        initialize(PropsErrors::class)
    }
}
