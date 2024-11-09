import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import extensions.jvmMainDependencies

plugins{
    id("desktop.application")
}

group = "dev.crypto.desktop.lab"
version = "1.0.0"

compose.desktop{
    application{
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Crypto labs"
            packageVersion = "1.0.0"
        }
        mainClass = "dev.crypto.desktop.lab.MainKt"
    }
}

jvmMainDependencies {
    implementation(project(":desktop:feature:firstlab"))
    implementation(project(":desktop:feature:secondlab"))
    implementation(project(":desktop:feature:thirdlab"))
}