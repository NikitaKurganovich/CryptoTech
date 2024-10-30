plugins{
    id("plugin.feature")
}

android {
    namespace = "dev.crypto.shared.logic.labfirst"
}


compose.resources {
    publicResClass = true
    packageOfResClass = "dev.crypto.labfirst.resources"
    generateResClass = always
}