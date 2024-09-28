plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.cazulabs.mychatapp"
    compileSdk = 34
    version = "1.0.0"

    task("appRelease") {
        doLast {
            file("./versionName.txt").writeText(version.toString())
        }
    }

    flavorDimensions.add("debug")
    productFlavors {
        create("free") {
            dimension = "debug"
            val appName = "MyChatApp"
            manifestPlaceholders["appName"] = appName
            applicationIdSuffix = ".demo"
            //versionName = "1.0"
            //versionNameSuffix = ".0"
            //versionCode = (versionName + versionNameSuffix).replace(".", "").toInt()
            //val apkName = "${appName}_$versionName$versionNameSuffix.apk"
            val apkName = "${appName}_$version.apk"

            // change app name block below
            buildOutputs.all {
                val variantOutputImpl = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
                variantOutputImpl.outputFileName =  apkName
            }
        }
    }

    defaultConfig {
        applicationId = "com.cazulabs.mychatapp"
        minSdk = 24
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
        viewBinding = true
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

    //FIREBASE
    implementation(platform("com.google.firebase:firebase-bom:31.4.0"))
    implementation("com.google.firebase:firebase-database-ktx")

    //Android Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    //DAGGER HILT
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-compiler:2.44")


}