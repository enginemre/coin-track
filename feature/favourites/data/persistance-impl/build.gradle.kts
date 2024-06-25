plugins {
    alias(libs.plugins.convention.android.data)
    alias(libs.plugins.convention.android.room)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.feature.favourite.data.persistanceimpl"
}

dependencies {
    implementation(projects.feature.favourites.data.persistance)
    implementation(projects.core.model)
}
