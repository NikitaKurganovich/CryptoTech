package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import setups.BaseComposePlugin
import setups.ComposeSetup

class CommonComposePlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(CommonLanguagePlugin::class.java)
                apply(ComposeSetup::class.java)
                apply(BaseComposePlugin::class.java)
            }
        }
    }
}