plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.home.data.api"
}

dependencies {
    api(projects.core.common)
    implementation(projects.home.domain)
    implementation(libs.kotlinx.coroutines.android)
}
