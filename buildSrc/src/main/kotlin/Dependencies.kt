object Versions {
    const val ktx = "1.6.10"
    const val gradle = "7.0.4"

    object Android {
        const val material = "1.4.0"
        const val appCompat = "1.4.0"
        const val constraintLayout = "2.1.2"
    }

}

object Dependencies {
    const val ktxGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.ktx}"
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"

    object Common {
        const val testCommonAnnotations = "test-annotations-common"
    }

    object Android {
        const val material = "com.google.android.material:material:${Versions.Android.material}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
    }
}