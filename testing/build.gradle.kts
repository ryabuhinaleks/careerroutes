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
    // ТОЛЬКО тестовые зависимости
    api("junit:junit:4.13.2")
    api("org.mockito:mockito-core:5.8.0")
    api("org.mockito:mockito-inline:5.2.0")
    api("org.mockito.kotlin:mockito-kotlin:5.2.1")
    api("org.mockito:mockito-junit-jupiter:5.8.0")
    api("androidx.arch.core:core-testing:2.2.0")
    api("io.reactivex.rxjava2:rxjava:2.2.21")
    api("io.reactivex.rxjava2:rxandroid:2.1.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")

    // Android зависимости если нужны
    api("androidx.fragment:fragment-ktx:1.6.2")
    api("androidx.appcompat:appcompat:1.6.1")
}