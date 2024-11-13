import extensions.commonMainDependencies

plugins{
    id("plugin.feature")
}

android {
    namespace = "dev.crypto.shared.logic.labthird"
}

commonMainDependencies {
    api(libs.nacular.measured)
    api(libs.vinceglb.filekit)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "dev.crypto.labthird.resources"
    generateResClass = always
}