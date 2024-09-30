package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByName
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.commonMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(extensions.getByName<KotlinMultiplatformExtension>("kotlin")) {
        sourceSets.commonMain.dependencies(block)
    }
}

fun Project.commonTestDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(extensions.getByName<KotlinMultiplatformExtension>("kotlin")) {
        sourceSets.commonTest.dependencies(block)
    }
}

fun Project.jvmMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(extensions.getByName<KotlinMultiplatformExtension>("kotlin")) {
        sourceSets.jvmMain.dependencies(block)
    }
}

fun Project.jvmTestDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(extensions.getByName<KotlinMultiplatformExtension>("kotlin")) {
        sourceSets.jvmTest.dependencies(block)
    }
}

fun Project.androidMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(extensions.getByName<KotlinMultiplatformExtension>("kotlin")) {
        sourceSets.androidMain.dependencies(block)
    }
}