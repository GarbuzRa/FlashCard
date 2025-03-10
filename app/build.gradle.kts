import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}

android {
    namespace = "com.example.flashcard"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.flashcard"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//после активации.
            /*
            1. Так как у properties свой синтаксис, то мне необходимо создать экземпляр класса,
            который сможет читать этот синтаксис.
            2. я обращаюсь к корневому обьекту и перу у него файл (по названию), в котором хранится нужный мне токен
            3.
             */
        val properties = Properties()
        val file = rootProject.file("develop.properties")
        properties.load(file.inputStream())
        buildConfigField("String", "ACCESS_TOKEN", "\"${properties["accessToken"]}\"")
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

    buildFeatures {
        viewBinding = true
        buildConfig = true //активирую
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

    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("androidx.room:room-common:2.6.1")
    val roomVersion = "2.6.1"
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.activity:activity:1.8.0")
    implementation("io.insert-koin:koin-android:3.4.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    implementation("androidx.room:room-runtime:$roomVersion")
    //ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.google.firebase:firebase-messaging-ktx:23.2.1")
    implementation("com.google.firebase:firebase-analytics-ktx:21.3.0")

}