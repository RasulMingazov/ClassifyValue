import com.jeanbernad.classifyvalue.buildsrc.Dependencies
import com.jeanbernad.classifyvalue.buildsrc.Base

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.jeanbernad.classifyvalue.feature.digit.data"
    compileSdk = Base.compileSDK

    defaultConfig {
        minSdk = Base.minSDK
        targetSdk = Base.targetSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        mlModelBinding = true
    }
    androidResources {
        noCompress("tflite")
    }
}

dependencies {
    implementation(project(":feature:digit:domain"))
    implementation(project(":core"))

    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.lifecycle)

    implementation(Dependencies.Dagger.hilt)
    kapt(Dependencies.Dagger.hiltCompiler)

    implementation(Dependencies.TenserFlow.gpu)
    implementation(Dependencies.TenserFlow.metadata)
    implementation(Dependencies.TenserFlow.support)

    testImplementation(Dependencies.Test.jUnit)
    debugImplementation(Dependencies.Compose.debug)

}
