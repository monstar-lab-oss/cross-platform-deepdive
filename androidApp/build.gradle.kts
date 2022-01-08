plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = App.compileSdk
    defaultConfig {
        applicationId = App.applicationId
        minSdk = App.minSdk
        targetSdk = App.targetSdk
        versionCode = App.versionCode
        versionName = App.versionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))

    with(Dependencies.Android) {
        implementation(material)
        implementation(appCompat)
        implementation(constraintLayout)
        implementation(lifecycleViewModel)
        implementation(lifecycleScope)
        implementation(coroutines)
        implementation(kodein)
    }
}