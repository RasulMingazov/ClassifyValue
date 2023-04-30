buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }

    dependencies {
        classpath(com.jeanbernad.classifyvalue.buildsrc.BuildPlugins.gradle)
        classpath(com.jeanbernad.classifyvalue.buildsrc.BuildPlugins.kotlin)
        classpath(com.jeanbernad.classifyvalue.buildsrc.BuildPlugins.hilt)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}