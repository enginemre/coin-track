plugins {
    alias(libs.plugins.convention.android.data)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.feature.home.data.domainimpl"
}

dependencies {
    implementation(projects.feature.home.data.api)
    implementation(projects.feature.home.data.persistence)
    implementation(projects.feature.home.domain)
    implementation(libs.paging)
}
