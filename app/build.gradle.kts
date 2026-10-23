plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.stockmanager"
    compileSdk = 34
    defaultConfig { minSdk = 24 }
}

dependencies {
    val room = "2.6.1"
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")   // soporte de corrutinas y Flow
    ksp("androidx.room:room-compiler:$room")
}
