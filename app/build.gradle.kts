plugins {
    alias(libs.plugins.convention.android.application)
    alias(libs.plugins.convention.android.application.compose)
    alias(libs.plugins.convention.android.hilt)
    alias(libs.plugins.convention.kotlinter)
}

android {

    defaultConfig {
        applicationId = "com.engin.cointrack"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    namespace = "com.engin.cointrack"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.authentication.ui)
    implementation(projects.home.ui)
    implementation(projects.home.domain)
    implementation(projects.home.data.domainImpl)
    implementation(projects.home.data.persistence)
    implementation(projects.home.data.persistenceImpl)
    implementation(projects.home.data.api)
    implementation(projects.home.data.apiImpl)
    implementation(projects.search.ui)
    implementation(projects.coinDetail.ui)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.android.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(libs.moshi.kotlin.core)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.androidx.compose.material3.adaptive)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.compose.ui.test.junit)
    androidTestImplementation(libs.hilt.android.testing)

    debugImplementation(libs.compose.ui.test.manifest)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.leakcanary)

    ksp(libs.moshi.kotlin.codegen)
}
