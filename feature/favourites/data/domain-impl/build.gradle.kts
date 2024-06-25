plugins {
    alias(libs.plugins.convention.android.data)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.feature.favourite.data.domainimpl"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.feature.favourites.data.persistance)
}
