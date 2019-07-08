/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.konan.resolve.symbols

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.Processor
import com.intellij.util.containers.MostlySingularMultiMap
import com.jetbrains.cidr.lang.symbols.OCQualifiedName
import com.jetbrains.cidr.lang.symbols.OCResolveContext
import com.jetbrains.cidr.lang.symbols.OCSymbol
import com.jetbrains.cidr.lang.symbols.OCVisibility
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol
import com.jetbrains.cidr.lang.types.OCObjectType
import org.jetbrains.konan.resolve.StubToSymbolTranslator
import org.jetbrains.kotlin.backend.konan.objcexport.ObjCClass

abstract class KotlinOCClassSymbol<State: KotlinOCClassSymbol.ClassState, Stub : ObjCClass<*>>
    : KotlinOCWrapperSymbol<State, Stub>, OCClassSymbol {

    private lateinit var qualifiedName: OCQualifiedName

    constructor(stub: Stub, project: Project, file: VirtualFile) : super(stub, project, file) {
        this.qualifiedName = OCQualifiedName.interned(stub.name)
    }

    constructor() : super()

    override fun isGlobal(): Boolean = true

    override fun getProtocolNames(): List<String> = state.protocolNames

    override fun getMembersCount(): Int = state.members?.size() ?: 0

    override fun <T : OCMemberSymbol?> processMembers(
        memberName: String?,
        memberClass: Class<out T>?,
        processor: Processor<in T>
    ): Boolean {
        val members = state.members ?: return true

        val myProcessor: Processor<OCMemberSymbol> = if (memberClass != null) {
            Processor { member -> !memberClass.isAssignableFrom(member.javaClass) || processor.process(member as T) }
        } else {
            processor as Processor<OCMemberSymbol>
        }

        return if (memberName == null) {
            members.processAllValues(myProcessor)
        } else {
            members.processForKey(memberName, myProcessor)
        }
    }

    override fun getDefinitionSymbol(project: Project): OCClassSymbol? = this

    override fun getInterfaceOrProtocol(project: Project): OCClassSymbol? = this

    override fun getImplementation(project: Project): OCImplementationSymbol? = null

    override fun getResolvedType(context: OCResolveContext, ignoringImports: Boolean): OCObjectType? =
        type.resolve(context, ignoringImports) as? OCObjectType

    override fun getParent(): OCSymbolWithQualifiedName? = null

    override fun getNameWithParent(context: OCResolveContext): String = name

    override fun getVisibility(): OCVisibility? = null

    override fun getQualifiedName(): OCQualifiedName = qualifiedName

    override fun dropSubstitution(): OCSymbol = this

    open class ClassState : StubState {
        val members: MostlySingularMultiMap<String, OCMemberSymbol>?
        lateinit var protocolNames: List<String>

        constructor(clazz: KotlinOCClassSymbol<*, *>, stub: ObjCClass<*>, project: Project) : super(stub) {
            this.protocolNames = stub.superProtocols
            val translator = StubToSymbolTranslator(project)
            var map: MostlySingularMultiMap<String, OCMemberSymbol>? = null
            for (member in stub.members) {
                val translatedMember = translator.translateMember(member, clazz, clazz.containingFile)
                if (translatedMember != null) {
                    if (map == null) map = MostlySingularMultiMap()
                    map.add(member.name, translatedMember)
                }
            }
            members = map
        }

        constructor() : super() {
            members = null
        }
    }
}