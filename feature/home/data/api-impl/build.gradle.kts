plugins {
    alias(libs.plugins.convention.android.data)
    alias(libs.plugins.convention.kotlinter)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.secrets)
}

android {
    namespace = "com.engin.cointrack.feature.home.data.apiimpl"
    buildFeatures {
        buildConfig = true
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}


dependencies {
    implementation(projects.feature.home.data.api)
    implementation(projects.feature.home.domain)


    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)

    api(projects.core.network)

    ksp(libs.moshi.kotlin.codegen)
}
