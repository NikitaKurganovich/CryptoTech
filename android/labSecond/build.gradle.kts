plugins {
    id("android.application")
}

android {
    namespace = "dev.crypto.labsecond"

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
    implementation(project(":shared:ui"))
    implementation(project(":shared:logic:labSecond"))
}