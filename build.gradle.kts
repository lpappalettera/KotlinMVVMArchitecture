// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion: String by extra("1.4.30")
    val navigationVersion: String by extra("2.3.2")

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}