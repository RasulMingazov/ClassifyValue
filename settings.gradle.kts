pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ClassifyValue"
include(":app")
include(":theme")
include(":core")
include(":core:presentation")
include(":core:data")
include(":core:domain")
include(":core:featureApi")
include(":core:stringResources")
include(":feature")
include(":feature:paint")
include(":feature:paintApi")
include(":feature:camera")
include(":feature:cameraApi")
include(":feature:paint:presentation")
include(":feature:camera:presentation")
