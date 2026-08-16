package seskar.compiler.union.extensions

import org.jetbrains.kotlin.KtFakeSourceElementKind
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.fakeElement
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase
import org.jetbrains.kotlin.fir.declarations.builder.buildRegularClass
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl
import org.jetbrains.kotlin.fir.declarations.origin
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension
import org.jetbrains.kotlin.fir.extensions.NestedClassGenerationContext
import org.jetbrains.kotlin.fir.extensions.predicateBasedProvider
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.scopes.kotlinScopeProvider
import org.jetbrains.kotlin.fir.symbols.SymbolInternals
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
import org.jetbrains.kotlin.fir.toEffectiveVisibility
import org.jetbrains.kotlin.fir.toFirResolvedTypeRef
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.name.SpecialNames
import seskar.compiler.common.backend.SeskarPluginKey
import seskar.compiler.jsany.extensions.jsAnyClassLikeType

private val JS_ANY = ClassId(
    FqName("kotlin.js"),
    Name.identifier("JsAny"),
)

internal class UnionCompanionGenerationExtension(session: FirSession) :
    FirDeclarationGenerationExtension(session) {

    private val matchedInterfaces by lazy {
        session.predicateBasedProvider
            .getSymbolsByPredicate(UnionPredicates.AnnotatedWithUnion.LOOKUP)
            .filterIsInstance<FirRegularClassSymbol>()
            .toSet()
    }

    private val FirClassLikeSymbol<*>.isUnion: Boolean
        get() = this is FirRegularClassSymbol
                && this in matchedInterfaces

    override fun getNestedClassifiersNames(
        classSymbol: FirClassSymbol<*>,
        context: NestedClassGenerationContext,
    ): Set<Name> {
        return if (classSymbol.isUnion)
            setOf(SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT)
        else
            emptySet()
    }

    override fun generateNestedClassLikeDeclaration(
        owner: FirClassSymbol<*>,
        name: Name,
        context: NestedClassGenerationContext,
    ): FirClassLikeSymbol<*>? {
        return if (
            owner is FirRegularClassSymbol &&
            owner.isUnion &&
            name == SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT
        ) generateCompanionDeclaration(owner)
        else null
    }

    private fun generateCompanionDeclaration(
        owner: FirRegularClassSymbol,
    ): FirRegularClassSymbol? {
        @OptIn(SymbolInternals::class)
        if (owner.companionObjectSymbol != null) {
            return null
        }

        val classId = owner.classId.createNestedClassId(SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT)
        return buildRegularClass {
            resolvePhase = FirResolvePhase.BODY_RESOLVE
            moduleData = session.moduleData
            origin = SeskarPluginKey.origin
            classKind = ClassKind.OBJECT
            scopeProvider = session.kotlinScopeProvider
            status = FirResolvedDeclarationStatusImpl(
                visibility = Visibilities.Public,
                modality = Modality.FINAL,
                effectiveVisibility = Visibilities.Public.toEffectiveVisibility(owner, forClass = true)
            ).apply {
                isExternal = true
                isCompanion = true
            }
            name = classId.shortClassName
            symbol = FirRegularClassSymbol(classId)
            source = owner.source?.fakeElement(KtFakeSourceElementKind.PluginGenerated)
            superTypeRefs += jsAnyClassLikeType()
                .toFirResolvedTypeRef(owner.source?.fakeElement(KtFakeSourceElementKind.PluginGenerated))
        }.symbol
    }
}

