import extensions.jvmMainDependencies

plugins{
    id("desktop.application")
}

jvmMainDependencies {
    implementation(project(":shared:logic:firstlab"))
}