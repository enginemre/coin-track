plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {
    implementation(projects.feature.home.domain)
    implementation(projects.core.model)
    implementation(libs.paging.common)
    implementation(libs.room.common)
}
