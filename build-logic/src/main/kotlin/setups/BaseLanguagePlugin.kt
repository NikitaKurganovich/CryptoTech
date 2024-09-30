package setups

import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

internal class BaseLanguagePlugin: Plugin<Project> {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(libs.plugins.jetbrains.kotlin.multiplatform.get().pluginId)
                apply(libs.plugins.kotlinx.serialization.get().pluginId)
            }

            with(extensions.getByType(KotlinMultiplatformExtension::class.java)) {
                compilerOptions {
                    apiVersion.set(KotlinVersion.KOTLIN_2_0)
                }
                sourceSets.commonMain {
                    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
                }
            }
        }
    }
}