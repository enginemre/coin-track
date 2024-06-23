plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.home.domain"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.paging)
    implementation(libs.kotlinx.coroutines.core)
}
