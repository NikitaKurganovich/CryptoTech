package plugins

import extensions.commonMainDependencies
import extensions.commonTestDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import setups.AndroidSetup

class FeaturePlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(CommonComposePlugin::class.java)
                apply(AndroidSetup::class.java)
            }
            commonMainDependencies {
                implementation(project(":base"))
            }
            commonTestDependencies {
                implementation(project(":basetest"))
            }
        }
    }
}