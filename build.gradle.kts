// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
//        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.46.1")
        classpath("com.google.gms:google-services:4.3.13")
        val nav_version = "2.5.0"
        //noinspection GradleDependency
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }

}




plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false

}