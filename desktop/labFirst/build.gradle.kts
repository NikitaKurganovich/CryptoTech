import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins{
    id("desktop.application")
}

group = "firstlab"
version = "1.0.0"

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