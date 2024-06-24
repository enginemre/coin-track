plugins {
    alias(libs.plugins.convention.android.data)
    alias(libs.plugins.convention.kotlinter)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.engin.cointrack.feature.coindetail.data.apiimpl"
}

dependencies {
    implementation(projects.feature.coinDetail.data.api)
    implementation(projects.feature.coinDetail.domain)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)

    api(projects.core.network)

    ksp(libs.moshi.kotlin.codegen)
}
