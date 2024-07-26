plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
}
android {
    namespace = "com.engin.cointrack.core.data"
}

dependencies {
    api(projects.core.common)
    api(projects.core.model)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.moshi.kotlin.core)

    ksp(libs.moshi.kotlin.codegen)
}
