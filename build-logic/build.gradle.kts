plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.bundles.gradleplugins)
}

gradlePlugin {
    plugins {
        register("plugin.feature"){
            id = "plugin.feature"
            implementationClass = "plugins.FeaturePlugin"
        }

        register("plugin.language"){
            id = "plugin.language"
            implementationClass = "plugins.CommonLanguagePlugin"
        }

        register("plugin.compose"){
            id = "plugin.compose"
            implementationClass = "plugins.CommonComposePlugin"
        }

        register("desktop.application"){
            id = "desktop.application"
            implementationClass = "apps.DesktopApplication"
        }

        register("android.application"){
            id = "android.application"
            implementationClass = "apps.AndroidApplication"
        }
    }
}