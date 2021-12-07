package org.jetbrains.kotlin.fir.plugin.fsi.services

import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar
import org.jetbrains.kotlin.fir.plugin.fsi.FirFsiExtensionRegistrar
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices

class ExtensionRegistrarConfigurator(testServices: TestServices) : EnvironmentConfigurator(testServices) {
    override fun registerCompilerExtensions(project: Project) {
        FirExtensionRegistrar.registerExtension(project, FirFsiExtensionRegistrar())
    }
}