package extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()

internal val Project.javaVersion: JavaVersion
    get() = JavaVersion.toVersion(libs.versions.java.get().toInt())

internal val Project.jvmTarget: JvmTarget
    get() = JvmTarget.fromTarget(this.javaVersion.majorVersion)

internal val Project.compose: ComposePlugin.Dependencies
    get() = this.extensions.findByType(KotlinMultiplatformExtension::class.java)?.compose
        ?: error("Compose not found")

internal
val Project.composeExtension: ComposeExtension
    get() =
        (this as ExtensionAware).extensions.getByName("compose") as ComposeExtension


internal
fun ComposeExtension.desktop(configure: Action<DesktopExtension>): Unit =
    (this as ExtensionAware).extensions.configure("desktop", configure)

internal
fun KotlinMultiplatformExtension.sourceSets(configure: Action<NamedDomainObjectContainer<KotlinSourceSet>>): Unit =
    (this as ExtensionAware).extensions.configure("sourceSets", configure)


internal val KotlinMultiplatformExtension.compose: ComposePlugin.Dependencies
    get() =
        (this as ExtensionAware).extensions.getByName("compose") as ComposePlugin.Dependencies


internal val Project.commonExtension: CommonExtension<*, *, *, *, *, *>
    get() = (
            extensions.findByType<LibraryExtension>()
                ?: extensions.findByType<ApplicationExtension>()
            ) as CommonExtension<*, *, *, *, *, *>

internal fun Project.configureKotlinOptions() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.apply{
                this@configureKotlinOptions.javaVersion
            }
            jvmTarget.set(this@configureKotlinOptions.jvmTarget)
            freeCompilerArgs.add(
                "-opt-in=" +
                        "kotlin.Experimental," +
                        "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                        "kotlinx.coroutines.FlowPreview," +
                        "androidx.paging.ExperimentalPagingApi"
            )
            if (project.findProperty("composeCompilerReports") == "true") {
                freeCompilerArgs.add(
                    listOf(
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                                project.layout.buildDirectory + "/compose_compiler",
                    ).toString()
                )
            }
            if (project.findProperty("composeCompilerMetrics") == "true") {
                freeCompilerArgs.add(
                    listOf(
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                                project.layout.buildDirectory + "/compose_compiler",
                    ).toString()
                )
            }
        }
    }
}

internal fun DependencyHandler.androidTestImplementation(dependencyNotation: Any) {
    this.add("androidTestImplementation", dependencyNotation)
}

internal fun Project.configureAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = libs.versions.compileSdk.get().toInt()

        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildFeatures {
            buildConfig = true
        }

        compileOptions {
            sourceCompatibility = project.javaVersion
            targetCompatibility = project.javaVersion
        }

        buildTypes {
            getByName("debug") {
                enableUnitTestCoverage = true
                enableAndroidTestCoverage = true
            }
            getByName("release") {
                enableUnitTestCoverage = false
                enableAndroidTestCoverage = false
            }
        }
    }

    dependencies {
        androidTestImplementation(libs.androidx.test.runner)
    }
}

val Project.applicationVersionCode: Int
    get() = 1

val Project.applicationVersionName: String
    get() = "1.0"