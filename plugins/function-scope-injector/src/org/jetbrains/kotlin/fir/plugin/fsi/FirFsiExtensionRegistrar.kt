package org.jetbrains.kotlin.fir.plugin.fsi

import com.intellij.mock.MockProject
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class FirFsiExtensionRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::FirFsiHelloInjector
        +::FirFsiFqnInjector
        +::FirFsiValInjector
        +::FirFsiByFunctionNameInjector
    }
}

class FirFsiComponentRegistrar : ComponentRegistrar {
    override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
        FirExtensionRegistrar.registerExtension(project, FirFsiExtensionRegistrar())
    }
}