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
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}