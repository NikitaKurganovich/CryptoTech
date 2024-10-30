plugins{
    id("plugin.feature")
}

android {
    namespace = "dev.crypto.shared.logic.labsecond"
}


compose.resources {
    publicResClass = true
    packageOfResClass = "dev.crypto.labsecond.resources"
    generateResClass = always
}