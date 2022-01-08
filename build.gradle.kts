buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.ktxGradlePlugin)
        classpath(Dependencies.gradle)
        classpath(Dependencies.sqlDelightGradle)
        classpath(Dependencies.buildKonfigGradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}