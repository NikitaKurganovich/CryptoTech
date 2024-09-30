package setups

import extensions.commonMainDependencies
import extensions.commonTestDependencies
import extensions.compose
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal class BaseComposePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project){
            with(pluginManager){
                apply(BaseLanguagePlugin::class.java)
                apply(libs.plugins.compose.compiler.get().pluginId)
                apply(libs.plugins.jetbrainsCompose.get().pluginId)
            }
            with(extensions.getByType(KotlinMultiplatformExtension::class.java)) {

                commonMainDependencies {
                    implementation(compose.animation)
                    implementation(compose.runtime)
                    implementation(compose.foundation)
                    implementation(compose.material3)
                    implementation(compose.materialIconsExtended)
                    implementation(compose.components.resources)
                    implementation(compose.uiUtil)
                    implementation(compose.ui)

                    implementation(libs.androidx.collections)
                    implementation(libs.kotlinx.immutablecollections)
                    implementation(libs.kotlinx.coroutines.core)
                }

                commonTestDependencies {
                    implementation(kotlin("test"))
                }
            }
        }
    }
}