package org.jetbrains.kotlin.fir.plugin.fsi.runners

import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.runners.AbstractKotlinCompilerTest

abstract class AbstractFirFsiDiagnosticTest : AbstractKotlinCompilerTest() {
    override fun TestConfigurationBuilder.configuration() {
        firWithPluginConfiguration()
    }
}

