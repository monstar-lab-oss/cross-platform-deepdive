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
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Android.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))

    with(Dependencies.Android) {
        implementation(material)
        implementation(appCompat)
        implementation(lifecycleViewModel)
        implementation(lifecycleScope)
        implementation(coroutines)
        implementation(kodein)
    }

    with(Dependencies.Android.Compose) {
        implementation(activity)
        implementation(material)
        implementation(animation)
        implementation(tooling)
        implementation(viewModel)
        implementation(coil)
    }
}