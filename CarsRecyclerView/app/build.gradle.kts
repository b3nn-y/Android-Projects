plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.bennysamuel.carsrecyclerview"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bennysamuel.carsrecyclerview"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

}

dependencies {

//    implementation("androidx.appcompat:appcompat:1.6.1")
//
//    // Support libraries
//
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//
//    // Android KTX
//    implementation("androidx.core:core-ktx:2.0.0")
//
//    // Room and Lifecycle dependencies
//    implementation("androidx.room:room-runtime:2.6.1")
//    annotationProcessor("androidx.room:room-compiler:2.6.1")
//    // To use Kotlin annotation processing tool (kapt)
//    kapt("androidx.room:room-compiler:2.7.0")
//    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
//
//    // ViewModel and LiveData
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
//
//    // Kotlin Extensions and Coroutines support for Room
//    implementation("androidx.room:room-ktx:2.6.1")
//
//    // Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:2.0.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:2.0.0")
//
//    // Navigation
//    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
//    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
//
//    // Testing
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}