plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // hilt dependencies
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    // navigation safeargs
//    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.pineneedle.pokemonapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pineneedle.pokemonapp"
        minSdk = 25
        targetSdk = 35
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

    //    enables view_binding
    buildFeatures{
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Navigation Dependencies
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.convertergson)

    //Viewmodel
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")

    //Hilt Dependencies
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
}

// hilt dependency
kapt {
    correctErrorTypes = true
}