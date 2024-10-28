import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import extensions.jvmMainDependencies

plugins{
    id("desktop.application")
}

group = "firstlab"
version = "1.0.0"

jvmMainDependencies{
    implementation(project(":base"))
}

compose.desktop{
    application{
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "CryptoLabFirst"
            packageVersion = "1.0.0"
        }
        mainClass = "firstlab.LabFirstKt"
    }
}