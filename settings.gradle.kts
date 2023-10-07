rootProject.name = "composite-build-tests"

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }
    includeBuild("gradle/plugins")
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include("subproject")
includeBuild("subbuild") {
    dependencySubstitution {
        substitute(module("com.foobar:subbuild")).using(project(":"))
    }
}
