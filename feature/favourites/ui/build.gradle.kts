plugins {
    alias(libs.plugins.convention.android.feature)
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.feature.favourite.ui"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(libs.androidx.material.pull.to.refresh)
    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.androidx.activity.compose)
}
