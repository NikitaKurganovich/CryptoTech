import extensions.commonMainDependencies

plugins{
    id("plugin.feature")
}

android {
    namespace = "dev.crypto.shared.logic.labthird"
}

commonMainDependencies {
    implementation(libs.nacular.measured)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "dev.crypto.labthird.resources"
    generateResClass = always
}