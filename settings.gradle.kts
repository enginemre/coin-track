pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "CoinTrack"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core")
include(":authentication")
include(":core:designsystem")
include(":authentication:ui")
include(":core:common")
include(":home:ui")
include(":search:ui")
include(":home:domain")
include(":home:data:domain-impl")
include(":home:data:api")
include(":home:data:api-impl")
include(":home:data:persistence")
include(":home:data:persistence-impl")
include(":core:network")
include(":core:model")
include(":coin-detail")
include(":coin-detail:ui")
include(":coin-detail:data")
include(":coin-detail:data:domain-impl")
include(":coin-detail:domain")
