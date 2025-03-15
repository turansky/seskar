package seskar.compiler.common.backend

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration
import org.jetbrains.kotlin.fir.declarations.utils.isExternal
import org.jetbrains.kotlin.fir.resolve.getContainingDeclaration
import org.jetbrains.kotlin.ir.declarations.IrClass

// WA for https://youtrack.jetbrains.com/issue/KT-67627
internal fun isReallyExternal(
    declaration: IrClass,
): Boolean {
    if (declaration.isExternal)
        return true

    val parent = declaration.parent as? IrClass
        ?: return false

    return isReallyExternal(parent)
}

internal tailrec fun isReallyExternal(
    declaration: FirClassLikeDeclaration,
    session: FirSession,
): Boolean {
    if (declaration.isExternal)
        return true

    val containingDeclaration = declaration.getContainingDeclaration(session)
        ?: return false

    return isReallyExternal(containingDeclaration, session)
}
