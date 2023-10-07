version = "2.0.0"
group = "com.foobar"

plugins {
    id("com.foobar.fizzbuzz")
}

kotlin {
    sourceSets["commonMain"].dependencies {
        implementation("com.foobar:subbuild")
        api(project("subproject"))
    }
}

android {
    namespace = "com.foobar.compositebuildtests"
    compileSdk = 33
    buildToolsVersion = "34.0.0"
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

tasks["check"].dependsOn(gradle.includedBuild("subbuild").task(":check"))
