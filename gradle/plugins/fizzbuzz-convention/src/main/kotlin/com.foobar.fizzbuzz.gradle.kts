plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
        publishLibraryVariants("debug")
    }

    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test"))
    }
}
