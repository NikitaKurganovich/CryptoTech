package apps

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import extensions.androidMainDependencies
import extensions.androidTestImplementation
import extensions.androidUnitTestDependencies
import extensions.applicationVersionCode
import extensions.applicationVersionName
import extensions.configureAndroid
import extensions.configureKotlinOptions
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import setups.BaseComposePlugin

class AndroidApplication : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(libs.plugins.android.application.get().pluginId)
                apply(BaseComposePlugin::class.java)
            }

            androidMainDependencies {
                implementation(project(":shared:ui"))
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.ui.text.google.fonts)
                implementation(libs.androidx.core.ktx)
                implementation(libs.ui)
                implementation(libs.material3)
                implementation(libs.ui.tooling.preview)
                implementation(libs.androidx.lifecycle.runtime.ktx)
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.lifecycle.viewmodel.compose)
            }
            androidUnitTestDependencies {
                implementation(libs.junit)
            }
            dependencies {
                androidTestImplementation(libs.runner)
                androidTestImplementation(libs.espresso.core)
            }
            with(extensions.getByType(KotlinMultiplatformExtension::class.java)) {
                androidTarget()

                with(extensions.getByType(BaseAppModuleExtension::class.java)) {
                    defaultConfig {
                        versionCode = project.applicationVersionCode
                        versionName = project.applicationVersionName
                    }

                    packaging {
                        resources {
                            excludes += listOf(
                                "META-INF/{AL2.0,LGPL2.1}",
                                "META-INF/DEPENDENCIES",
                                "META-INF/DEPENDENCIES.txt",
                                "META-INF/LICENSE",
                                "META-INF/LICENSE.txt",
                                "META-INF/LICENSE-FIREBASE.txt",
                                "META-INF/NOTICE",
                                "META-INF/NOTICE.txt",
                                "META-INF/*.kotlin_module",
                                "META-INF/versions/9/previous-compilation-data.bin"
                            )
                        }
                    }
                }
            }
            configureKotlinOptions()


            extensions.configure<ApplicationExtension> {
                configureAndroid(this)
                defaultConfig {
                    applicationId = "dev.example.crypto"
                    targetSdk = libs.versions.targetSdk.get().toInt()
                    versionCode = project.applicationVersionCode
                    versionName = project.applicationVersionName
                }

            }
        }
    }
}