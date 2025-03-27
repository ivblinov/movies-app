plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.examples.moviesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.examples.moviesapp"
        minSdk = 29
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Dagger:
    implementation (libs.dagger)
    ksp (libs.dagger.compiler)

    // Glide:
    implementation(libs.glide)

    // Navigation:
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.navigation.ui)

    // RecyclerView:
    implementation(libs.androidx.recyclerview)

    // Retrofit:
    implementation(libs.retrofit)

    // Moshi:
    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    ksp(libs.moshi.kotlin.codegen)

    // ViewModel:
    implementation(libs.androidx.viewmodel)
    implementation(libs.androidx.fragment.ktx)

    // Tests:
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}