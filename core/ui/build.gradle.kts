plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.core.ui"
}

dependencies {
    api(libs.compose.material.icons.extended)
    api(libs.compose.material3)
    api(libs.compose.ui.tooling.preview)
    api(libs.compose.ui.util)
    api(libs.androidx.navigation.compose)

    debugApi(libs.compose.ui.tooling)

    implementation(projects.core.designsystem)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.kotlinx.coroutines.core)
}
