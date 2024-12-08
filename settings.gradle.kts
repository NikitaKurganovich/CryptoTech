@file:Suppress("UnstableApiUsage")

import org.gradle.api.initialization.resolve.RepositoriesMode.FAIL_ON_PROJECT_REPOS

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }
    }
}

plugins{
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.5.0")
}

rootProject.name = "crypto-decoding"

includeBuild("build-logic")

include(":base")
include(":basetest")

include(":desktop:lab")

include(":desktop:feature:firstlab")
include(":desktop:feature:secondlab")
include(":desktop:feature:thirdlab")
include(":desktop:feature:seventhUtility")

include(":android:labFirst")
include(":android:labsecond")
include(":android:labthird")

include(":shared:logic:firstlab")
include(":shared:logic:secondlab")
include(":shared:logic:thirdlab")

include(":shared:ui")