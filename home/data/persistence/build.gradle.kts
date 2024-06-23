plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.convention.android.room)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.home.data.persistence"
}

dependencies {
    implementation(projects.home.domain)
    implementation(libs.paging)
}
