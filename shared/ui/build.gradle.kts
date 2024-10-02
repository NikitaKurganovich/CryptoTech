import extensions.androidMainDependencies

plugins{
    id("plugin.feature")
}

android {
    namespace = "dev.crypto.shared.ui"
}

androidMainDependencies {
    implementation(libs.androidx.ui.text.google.fonts)
}