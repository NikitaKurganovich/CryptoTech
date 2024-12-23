import extensions.androidMainDependencies

plugins {
    id("android.application")
}

android {
    namespace = "dev.crypto.labfirst"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

androidMainDependencies {
    implementation(project(":shared:logic:firstlab"))
}