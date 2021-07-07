import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "app.mvvm.architecture"
        minSdk = 26
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
    composeOptions {
        val composeVersion: String by rootProject.extra
        kotlinCompilerExtensionVersion = composeVersion
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    val localProperties = Properties()
    localProperties.load(rootProject.file("local.properties").inputStream())

    val newsApiKey = "NEWS_API_KEY"

    flavorDimensions.add("default")
    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            isDefault = true
            buildConfigString(newsApiKey, localProperties.getProperty("newsApiKeyDev"))
        }
        create("tst") {
            applicationIdSuffix = ".tst"
            buildConfigString(newsApiKey, localProperties.getProperty("newsApiKeyTst"))
        }
        create("acc") {
            applicationIdSuffix = ".acc"
            buildConfigString(newsApiKey, localProperties.getProperty("newsApiKeyAcc"))
        }
        create("prd") {
            buildConfigString(newsApiKey, localProperties.getProperty("newsApiKeyPrd"))
        }
    }
}

dependencies {

    // Kotlin Coroutines
    val coroutinesVersion = "1.4.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")

    // AndroidX
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.fragment:fragment-ktx:1.3.5")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.browser:browser:1.3.0")

    // App Startup
    implementation("androidx.startup:startup-runtime:1.0.0")

    // Dagger Hilt
    val hiltVersion: String by rootProject.extra
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    // Dagger Hilt for instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptAndroidTest("com.google.dagger:hilt-compiler:$hiltVersion")
    // Dagger Hilt for local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptTest("com.google.dagger:hilt-compiler:$hiltVersion")

    // Jetpack Compose
    val composeVersion: String by rootProject.extra
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.foundation:foundation-layout:$composeVersion")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    implementation("androidx.compose.animation:animation:$composeVersion")
    implementation("androidx.activity:activity-compose:1.3.0-rc01")
    androidTestImplementation("androidx.compose.ui:ui-test:$composeVersion")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

    // Compose Material
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-core:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")

    // Android Material
    implementation("com.google.android.material:material:1.3.0")

    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha03")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")

    // Lifecycle Components
    val lifecycleVersion = "2.3.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")

    // Accompanist
    val accompanistVersion = "0.13.0"
    implementation("com.google.accompanist:accompanist-insets:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanistVersion")

    // Logging
    implementation("com.jakewharton.timber:timber:4.7.1")

    // Retrofit
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    // OkHttp
    val okHttpVersion = "4.9.0"
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")

    // Room
    val roomVersion = "2.3.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // Unit testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.arch.core:core-testing:2.1.0")

    // Mockk
    testImplementation("io.mockk:mockk:1.10.6")

    // Instrumentation testing
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    kotlinOptions.allWarningsAsErrors = true
}

// For flavors usage
fun com.android.build.api.dsl.ApplicationProductFlavor.buildConfigBoolean(
    name: String,
    value: Boolean
) = buildConfigField("Boolean", name, value.toString())

fun com.android.build.api.dsl.ApplicationProductFlavor.buildConfigString(
    name: String,
    value: String
) = buildConfigField("String", name, "\"$value\"")

fun com.android.build.api.dsl.ApplicationProductFlavor.buildConfigInt(name: String, value: Int) =
    buildConfigField("int", name, "$value")