plugins {
    alias(libs.plugins.convention.android.feature)
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.feature.coindetail.ui"
}

dependencies {
    implementation(projects.feature.coinDetail.domain)
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(libs.androidx.activity.compose)
}
