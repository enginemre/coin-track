plugins {
    alias(libs.plugins.convention.android.feature)
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.convention.kotlinter)
}
android {
    namespace = "com.engin.cointrack.feature.home.ui"
}

dependencies {
    implementation(projects.feature.home.domain)
    implementation(projects.core.model)
    implementation(libs.coil.kt.compose)
    implementation(libs.coil.kt)
    implementation(libs.androidx.material.pull.to.refresh)
    implementation(libs.compose.paging)
    implementation(libs.androidx.activity.compose)
}
