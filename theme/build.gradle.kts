import com.jeanbernad.classifyvalue.buildsrc.Dependencies
import com.jeanbernad.classifyvalue.buildsrc.Base

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jeanbernad.classifyvalue.theme"
    compileSdk = Base.compileSDK

    defaultConfig {
        minSdk = Base.minSDK
        targetSdk = Base.targetSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}

dependencies {
    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.lifecycle)

    implementation(Dependencies.Compose.ui)

    implementation(Dependencies.Android.material)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.extJUnit)
    androidTestImplementation(Dependencies.Test.composeTestManifest)
    debugImplementation(Dependencies.Compose.debug)
    androidTestImplementation(Dependencies.Test.composeUiTest)
}