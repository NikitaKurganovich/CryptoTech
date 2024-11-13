plugins {
    id("android.application")
}

android {
    namespace = "dev.crypto.labthird"

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

dependencies {
    implementation(project(":shared:logic:thirdlab"))
}