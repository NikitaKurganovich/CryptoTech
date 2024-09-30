package apps

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import extensions.androidMainDependencies
import extensions.applicationVersionCode
import extensions.applicationVersionName
import extensions.configureAndroid
import extensions.configureKotlinOptions
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import setups.BaseComposePlugin

class AndroidApplication: Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(libs.plugins.android.application.get().pluginId)
                apply(BaseComposePlugin::class.java)
            }

            androidMainDependencies {
                implementation(libs.androidx.activity.compose)
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

                signingConfigs {

                }

                buildTypes {
                    getByName("release") {
                        signingConfig = signingConfigs.getByName("release")
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro",
                        )
                    }
                }
            }
        }
    }
}