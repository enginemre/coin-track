plugins {
    alias(libs.plugins.convention.android.data)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.feature.coindetail.data.domainimpl"
}

dependencies {
    implementation(projects.feature.coinDetail.domain)
    implementation(projects.feature.coinDetail.data.api)
}
