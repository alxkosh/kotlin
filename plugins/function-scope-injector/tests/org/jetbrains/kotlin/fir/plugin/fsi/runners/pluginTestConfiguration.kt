package org.jetbrains.kotlin.fir.plugin.fsi.runners

import org.jetbrains.kotlin.fir.plugin.fsi.services.ExtensionRegistrarConfigurator
import org.jetbrains.kotlin.fir.plugin.fsi.services.PluginAnnotationsProvider
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.directives.FirDiagnosticsDirectives.ENABLE_PLUGIN_PHASES
import org.jetbrains.kotlin.test.directives.FirDiagnosticsDirectives.FIR_DUMP
import org.jetbrains.kotlin.test.runners.baseFirDiagnosticTestConfiguration

fun TestConfigurationBuilder.firWithPluginConfiguration() {
    baseFirDiagnosticTestConfiguration()

    defaultDirectives {
        +ENABLE_PLUGIN_PHASES
        +FIR_DUMP
    }

    useConfigurators(
        ::PluginAnnotationsProvider,
        ::ExtensionRegistrarConfigurator
    )
}