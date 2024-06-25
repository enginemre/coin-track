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
include(":core:designsystem")
include(":core:network")
include(":core:model")
include(":core:ui")
include(":core:data")
include(":core:common")
include("feature:authentication")
include("feature:authentication:ui")
include("feature:home:ui")
include("feature:search:ui")
include("feature:home:domain")
include("feature:home:data:domain-impl")
include("feature:home:data:api")
include("feature:home:data:api-impl")
include("feature:home:data:persistence")
include("feature:home:data:persistence-impl")
include("feature:coin-detail")
include("feature:coin-detail:ui")
include("feature:coin-detail:data:domain-impl")
include("feature:coin-detail:domain")
include("feature:coin-detail:data:api")
include("feature:coin-detail:data:api-impl")
include(":feature:favourites")
include(":feature:favourites:ui")
include(":feature:favourites:domain")
include(":core:domain")
include(":feature:favourites:data:domain-impl")
include(":feature:favourites:data:persistance")
include(":feature:favourites:data:persistance-impl")
