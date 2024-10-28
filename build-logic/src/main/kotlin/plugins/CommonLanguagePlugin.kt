package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import setups.AndroidSetup
import setups.BaseLanguagePlugin

class CommonLanguagePlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(BaseLanguagePlugin::class.java)
                apply(AndroidSetup::class.java)
            }
            with(extensions.getByType(KotlinMultiplatformExtension::class.java)) {
                jvm("desktop")
                androidTarget()
            }
        }
    }
}