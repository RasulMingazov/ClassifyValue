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
