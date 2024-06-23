plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.room)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.home.data.persistenceimpl"
}

dependencies {
    implementation(projects.home.data.persistence)
    implementation(projects.home.domain)
    implementation(projects.core.common)

}
