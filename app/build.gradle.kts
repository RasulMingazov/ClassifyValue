import com.jeanbernad.classifyvalue.buildsrc.Dependencies
import com.jeanbernad.classifyvalue.buildsrc.Base

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.jeanbernad.classifyvalue"
    compileSdk = Base.compileSDK

    defaultConfig {
        applicationId = "com.jeanbernad.classifyvalue"
        minSdk = Base.minSDK
        targetSdk = Base.targetSDK
        versionCode = Base.versionCode
        versionName = Base.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
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
    implementation(Dependencies.Core.javax)
    implementation(Dependencies.Core.coroutine)

    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.preview)
    implementation(Dependencies.Compose.navigation)

    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.splash)
    implementation(Dependencies.Android.animation)
    implementation(Dependencies.Android.drawbox)

    implementation(Dependencies.Dagger.hilt)
    kapt(Dependencies.Dagger.hiltCompiler)
    implementation(Dependencies.Dagger.hiltCompose)

    implementation(Dependencies.TenserFlow.support)
    implementation(Dependencies.TenserFlow.metadata)
    implementation(Dependencies.TenserFlow.gpu)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.extJUnit)
    androidTestImplementation(Dependencies.Test.composeTestManifest)
    debugImplementation(Dependencies.Compose.debug)
    androidTestImplementation(Dependencies.Test.composeUiTest)
}