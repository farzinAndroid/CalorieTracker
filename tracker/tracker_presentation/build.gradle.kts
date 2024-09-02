plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id ("com.google.devtools.ksp")
}

android {
    namespace = "com.farzin.tracker_presentation"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources.excludes.addAll(
            listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
            )
        )
    }
    testOptions {
        packaging {
            jniLibs {
                useLegacyPackaging = true
            }
        }
        unitTests.all {
            it.jvmArgs("-Djdk.attach.allowAttachSelf=true")
        }
    }
}

dependencies {

    implementation(project(":tracker:tracker_domain"))
    implementation(project(":core"))
    implementation(project(":core-ui"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //hilt di
    implementation (libs.hilt.android)
    ksp (libs.hilt.compiler)
    implementation (libs.hilt.android.testing)
    implementation (libs.androidx.hilt.navigation.compose)



    // moshi
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.moshi)

    // coroutine test
    implementation (libs.kotlinx.coroutines.test)

    // google truth
    implementation (libs.truth)

    //mock
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)
//    testImplementation("org.mockito:mockito-inline:2.7.21")
//    androidTestImplementation("io.mockk:mockk-android-inline:1.13.12")
    testImplementation(libs.mockwebserver)
    implementation("com.github.wseemann:FFmpegMediaMetadataRetriever-core:1.0.15")
    implementation("com.github.wseemann:FFmpegMediaMetadataRetriever-native:1.0.15")

    // turbine
    implementation (libs.turbine)

    // test runner
    implementation (libs.androidx.runner)




    //coil - load image from url
    implementation (libs.coil.compose)
}