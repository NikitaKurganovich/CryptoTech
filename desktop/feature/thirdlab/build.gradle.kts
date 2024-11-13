import extensions.jvmMainDependencies

plugins{
    id("desktop.application")
}

jvmMainDependencies {
    api(project(":shared:logic:thirdlab"))
}