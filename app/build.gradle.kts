plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.carlos.task"
    compileSdk = 36   // <- precisa ser 36 por causa do core-ktx 1.17.0 e activity 1.10.1

    defaultConfig {
        applicationId = "com.carlos.task"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    viewBinding {
        enable = true
    }

    // Se não estiver usando DataBinding (layouts com <layout>), não precisa habilitar:
    // buildFeatures {
    //     dataBinding = true
    // }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // ---------------------------
    //      FIREBASE (BOM)
    // ---------------------------

    // BOM do Firebase (controla as versões internamente)
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))

    // Auth com extensões KTX (sem versão explícita – herda do BOM)
    implementation("com.google.firebase:firebase-auth-ktx")

    // ---------------------------

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
