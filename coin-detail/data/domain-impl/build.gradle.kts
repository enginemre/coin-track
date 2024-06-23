plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.coindetail.data.domainimpl"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.coinDetail.domain)
    implementation(projects.core.common)
}
