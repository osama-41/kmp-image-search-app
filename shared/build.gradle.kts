import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)

    // Require for Jetpack Compose
    alias(libs.plugins.composeCompiler)
    // Annotation Processor
    alias(libs.plugins.ksp)
    // Native Coroutines Support for Kotlin Multiplatform
    alias(libs.plugins.native.coroutines)
    // Serialization
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }

        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.material3)
            implementation(libs.androidx.activity.compose)

            // koin
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // ktor
            implementation(libs.ktor.client.android)
        }

        commonMain.dependencies {
            // Kmp observable viewmodel
            implementation(libs.kmp.observableviewmodel.core)

            implementation(libs.compose.runtime)

            // koin
            implementation(libs.koin.core)

            // Ktor Client
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.serialization)

            // coil
            implementation(libs.coil)
            implementation(libs.coil.ktor)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "dev.himanshu.imagesearchapp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}