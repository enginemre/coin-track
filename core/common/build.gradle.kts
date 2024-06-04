plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
}

android {
    namespace = "com.engin.cointrack.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
