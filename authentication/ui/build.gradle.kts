plugins {
    alias(libs.plugins.convention.android.feature)
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.convention.kotlinter)
}

android {

    namespace = "com.engin.cointrack.authentication.ui"
}

dependencies {
    implementation(libs.androidx.activity.compose)
}
