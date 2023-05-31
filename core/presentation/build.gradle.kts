import com.jeanbernad.classifyvalue.buildsrc.Dependencies
import com.jeanbernad.classifyvalue.buildsrc.Base

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.jeanbernad.classifyvalue.core.presentation"
    compileSdk = Base.compileSDK

    defaultConfig {
        minSdk = Base.minSDK
        targetSdk = Base.targetSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(project(":theme"))
    implementation(project(":core:stringResources"))
    implementation(project(":core:featureApi"))
    implementation(project(":core"))

    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.lifecycle)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.preview)
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.coil)

    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.splash)
    implementation(Dependencies.Android.animation)
    implementation(Dependencies.Android.drawbox)

    implementation(Dependencies.Compose.navigation)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.extJUnit)
    androidTestImplementation(Dependencies.Test.composeTestManifest)
    debugImplementation(Dependencies.Compose.debug)
    androidTestImplementation(Dependencies.Test.composeUiTest)


}