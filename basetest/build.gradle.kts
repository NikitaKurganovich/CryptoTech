import extensions.commonMainDependencies

plugins{
    id("plugin.language")
}

android {
    namespace = "dev.crypto.base"
}

commonMainDependencies {
    implementation(kotlin("test"))
}