import extensions.jvmTestDependencies

plugins{
    id("desktop.application")
}

jvmTestDependencies {
    implementation(project(":shared:logic:secondlab"))
    implementation(project(":basetest"))
}