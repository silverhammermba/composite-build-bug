version = "0.0.0"
group = "com.foobar"

plugins {
    id("com.foobar.fizzbuzz")
}

android {
    namespace = "com.foobar.subbuild"
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
