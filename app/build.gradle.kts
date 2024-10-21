plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.gekonapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gekonapp"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
    sourceSets {
        getByName("main").java.srcDirs("build/generated/source/navigation-args")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-ktx:1.9.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.5")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.5")
    implementation ("androidx.fragment:fragment-ktx:1.8.3")

    implementation ("androidx.room:room-runtime:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")

    // Для поддержки RxJava или Coroutine
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    implementation("androidx.recyclerview:recyclerview:1.3.2")

    implementation("androidx.navigation:navigation-fragment-ktx:2.8.0")  // или ваша версия
    implementation("androidx.navigation:navigation-ui-ktx:2.8.0")

}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}