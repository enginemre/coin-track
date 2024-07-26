plugins {
    alias(libs.plugins.convention.android.data)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.feature.authentication.data.apiimpl"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.feature.authentication.data.api)

    implementation(libs.kotlinx.coroutines.core)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
}
