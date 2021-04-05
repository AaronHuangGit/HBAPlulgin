package com.hisoybean.plugin.constants


class AndroidCommonDeps {
    object Versions {
        const val kotlinStdlib = "1.4.32"
        const val androidXCore = "1.3.2"
        const val appCompat = "1.2.0"
        const val androidMaterial = "1.3.0"
    }
    companion object {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinStdlib}"
        const val androidXCore = "androidx.core:core-ktx:${Versions.androidXCore}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
    }
}

