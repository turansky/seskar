package io.github.turansky.seskar

import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters1
import org.jetbrains.kotlin.js.resolve.diagnostics.ErrorsJs.*
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtPrimaryConstructor
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.diagnostics.DiagnosticSuppressor

private const val EXTERNAL_PRIVATE_CONSTRUCTOR = "private member of class"

class K1DiagnosticSuppressor : DiagnosticSuppressor {
    override fun isSuppressed(
        diagnostic: Diagnostic,
    ): Boolean =
        false

    override fun isSuppressed(
        diagnostic: Diagnostic,
        bindingContext: BindingContext?,
    ): Boolean {
        bindingContext ?: return false

        val psiElement = diagnostic.psiElement
        val factory = diagnostic.factory

        return when (factory) {
            WRONG_EXTERNAL_DECLARATION,
            -> when (psiElement) {
                is KtPrimaryConstructor,
                -> diagnostic.messageParameter == EXTERNAL_PRIVATE_CONSTRUCTOR

                else -> false
            }

            EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER,
            -> true

            NESTED_CLASS_IN_EXTERNAL_INTERFACE,
            -> psiElement is KtObjectDeclaration && psiElement.isCompanion()

            else -> false
        }
    }
}

private val Diagnostic.messageParameter: String?
    get() = when (this) {
        is DiagnosticWithParameters1<*, *> -> a as? String
        else -> null
    }
