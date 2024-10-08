plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("com.google.dagger.hilt.android")
    id ("com.google.devtools.ksp")
}

android {
    namespace = "com.farzin.tracker_data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    packaging {
        resources.excludes.addAll(
            listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
            )
        )
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":tracker:tracker_domain"))


    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(libs.androidx.ui.test.junit4)

    //hilt di
    implementation (libs.hilt.android)
    ksp (libs.hilt.compiler)
    implementation (libs.hilt.android.testing)



    // coroutine test
    implementation (libs.kotlinx.coroutines.test)

    // google truth
    implementation (libs.truth)

    //mock
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)
    testImplementation(libs.mockwebserver)

    // turbine
    implementation (libs.turbine)

    // test runner
    implementation (libs.androidx.runner)

    //room
    implementation (libs.androidx.room.runtime)
    ksp (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    //retrofit
    implementation (libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)

    // moshi
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.moshi)
    implementation (libs.converter.moshi)
}