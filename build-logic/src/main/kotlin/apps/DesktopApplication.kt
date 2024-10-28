package apps

import extensions.compose
import extensions.jvmMainDependencies
import extensions.jvmTestDependencies
import extensions.libs
import extensions.sourceSets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import setups.BaseComposePlugin

class DesktopApplication: Plugin<Project> {
    override fun apply(project: Project) {
        with(project){
            with(pluginManager){
                apply(BaseComposePlugin::class.java)
            }
            with(extensions.getByType(KotlinMultiplatformExtension::class.java)){
                jvm{
                    withJava()
                }
                sourceSets {
                    jvmMainDependencies {
                        implementation(project(":base"))
                        implementation(project(":shared:ui"))
                        implementation(compose.desktop.currentOs)
                        implementation(libs.voyager.navigator)
                        implementation(libs.voyager.screenModel)
                        implementation(libs.kotlinx.coroutines.swing)
                    }
                    jvmTestDependencies {
                        implementation(kotlin("test"))
                    }
                }
            }
        }
    }
}