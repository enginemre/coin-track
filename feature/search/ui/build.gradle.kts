plugins {
    alias(libs.plugins.convention.android.feature)
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.feature.search.ui"
}

dependencies {
    implementation(projects.feature.search.domain)
    implementation(projects.core.model)
    implementation(projects.core.ui)
    implementation(libs.compose.paging)
    implementation(libs.androidx.activity.compose)
}
