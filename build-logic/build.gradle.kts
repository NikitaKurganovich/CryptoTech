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
        register("application.feature"){
            id = "application.feature"
            implementationClass = "plugins.FeaturePlugin"
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
