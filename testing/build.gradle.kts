plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.testing"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    // ДОБАВЬ ЭТО:
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    // ВАЖНО: Отключаем AndroidManifest для чистой библиотеки
    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")
}

dependencies {
    api(libs.junit)
    api(libs.mockito.core)
    api(libs.mockito.inline)
    api(libs.mockito.kotlin)
    api(libs.mockito.junit.jupiter)
    api(libs.androidx.core.testing)
    api(libs.rxjava2)
    api(libs.rxandroid)
    api(libs.kotlinx.coroutines.test)

    api(libs.androidx.fragment.ktx)
    api(libs.androidx.appcompat)
}