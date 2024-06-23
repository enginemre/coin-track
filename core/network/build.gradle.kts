plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.secrets)
    alias(libs.plugins.ksp)
    alias(libs.plugins.convention.kotlinter)
}

android {
    namespace = "com.engin.cointrack.core.network"

    buildFeatures {
        buildConfig = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {

    api(libs.okhttp)
    api(libs.okhttp.logging)
    api(libs.kotlinx.coroutines.android)
    api(libs.retrofit)
    api(libs.retrofit.converter.moshi)
    api(libs.moshi.kotlin.core)
    debugApi(libs.chucker)
    releaseApi(libs.chucker.no.op)
    ksp(libs.moshi.kotlin.codegen)
}
