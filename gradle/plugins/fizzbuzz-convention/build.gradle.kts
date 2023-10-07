plugins {
    `kotlin-dsl`
    kotlin("jvm")
}

dependencies {
    // our custom plugin applies Kotlin 1.9.10 to the project that uses it
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    implementation("org.jetbrains.kotlin.multiplatform:org.jetbrains.kotlin.multiplatform.gradle.plugin:1.9.10")
    implementation("com.android.library:com.android.library.gradle.plugin:8.1.1")
}
