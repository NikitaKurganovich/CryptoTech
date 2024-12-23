import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import extensions.jvmMainDependencies

plugins{
    id("desktop.application")
}

buildscript {
    dependencies {
        classpath(libs.proguard.gradle)
    }
}

group = "dev.crypto.desktop.lab"
version = "1.0.0"

jvmMainDependencies {
    implementation(project(":desktop:feature:firstlab"))
    implementation(project(":desktop:feature:secondlab"))
    implementation(project(":desktop:feature:thirdlab"))
}

compose.desktop{
    application{
        buildTypes.release.proguard {
            configurationFiles.from(project.file("compose-proguard.pro"))
        }
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            packageName = "Crypto labs"
            packageVersion = "1.0.0"
            description = "Bundle of a labs for subject Cryptographic Technologies"
            copyright = "© 2024 Nikita Kurganovich"

            outputBaseDir.set(project.rootDir.resolve("desktopRelease"))

            appResourcesRootDir.set(project.layout.projectDirectory.dir("resources"))
            windows {
                iconFile.set(project.file("icon/Icon.ico"))
                dirChooser = true
            }
        }
        mainClass = "dev.crypto.desktop.lab.MainKt"
    }
}

