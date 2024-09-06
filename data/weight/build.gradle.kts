plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "nz.mikhailov.motiv.data.weight"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
//    sourceSets {
//        androidTest.assets.srcDirs += files("$projectDir/schemas".toString())
//    }

//    buildTypes {
//        release {
//            isMinifyEnabled = false
//        }
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
}

dependencies {
    implementation(project(":core:database"))
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    androidTestImplementation(libs.room.testing)
}
