    plugins {
    id("com.android.application")
        id("com.google.gms.google-services")
}

android {
    namespace = "com.example.food_app"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.food_app"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:32.4.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.annotation:annotation:1.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("com.google.firebase:firebase-analytics:21.4.0")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.firebaseui:firebase-ui-auth:7.2.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10")
    implementation ("androidx.room:room-runtime:2.4.0") // Reemplaza con la versión más reciente de Room
    annotationProcessor ("androidx.room:room-compiler:2.4.0") // Reemplaza con la versión más reciente de Room

}