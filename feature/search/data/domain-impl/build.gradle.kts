plugins {
    alias(libs.plugins.convention.android.data)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.feature.search.data.domainimpl"
}

dependencies {
    implementation(projects.feature.search.data.api)
    implementation(projects.feature.search.domain)
    implementation(libs.paging)
    implementation(projects.core.model)
    implementation(projects.core.common)
}
