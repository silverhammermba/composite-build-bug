rootProject.name = "subbuild"

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }
    includeBuild("../gradle/plugins")
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
