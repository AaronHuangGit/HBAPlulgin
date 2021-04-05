package com.hisoybean.plugin.constants

import org.gradle.api.JavaVersion

object AndroidExtensionConstants {
    const val compileSdkVersion = 29
    const val minSdkVersion = 19
    const val targetSdkVersion = 29
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val compileOptionsJavaVersion = JavaVersion.VERSION_1_8
    const val kotlinOptionJvmTarget = "1.8"
}