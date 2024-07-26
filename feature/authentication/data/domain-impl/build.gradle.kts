plugins {
    alias(libs.plugins.convention.android.data)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.feature.authentication.data.domainimpl"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.feature.authentication.domain)
    implementation(projects.feature.authentication.data.api)
}
