plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.home.data.domainimpl"
}

dependencies {
    implementation(projects.home.data.api)
    implementation(projects.home.data.persistence)
    implementation(projects.home.domain)
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(libs.paging)
}
