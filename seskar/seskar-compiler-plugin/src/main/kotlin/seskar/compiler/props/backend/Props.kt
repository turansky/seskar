package seskar.compiler.props.backend

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name.identifier
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.resolve.descriptorUtil.getSuperInterfaces

private val REACT_PACKAGE_ID = identifier("react")
private val REACT_PACKAGE = FqName(REACT_PACKAGE_ID.identifier)
private val PROPS = REACT_PACKAGE.child(identifier("Props"))

private val ClassDescriptor.isProps: Boolean
    get() = isExternal && fqNameSafe == PROPS

internal val ClassDescriptor.implementsPropsDirectly: Boolean
    get() = getSuperInterfaces()
        .any { it.isProps }

internal val ClassDescriptor.implementsProps: Boolean
    get() {
        if (implementsPropsDirectly) {
            return true
        }

        return getSuperInterfaces()
            .any { it.implementsProps }
    }
