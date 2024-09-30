package plugins

import extensions.commonMainDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import setups.AndroidSetup
import setups.CommonComposePlugin

class FeaturePlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(CommonComposePlugin::class.java)
                apply(AndroidSetup::class.java)
            }
            commonMainDependencies {

            }
        }
    }
}