plugins {
    alias(libs.plugins.convention.android.data)
    alias(libs.plugins.convention.kotlinter)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.engin.cointrack.feature.search.data.apiimpl"
}

dependencies {
    implementation(projects.feature.search.data.api)
    implementation(projects.feature.search.domain)
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.common)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)

    api(projects.core.network)

    ksp(libs.moshi.kotlin.codegen)
}
