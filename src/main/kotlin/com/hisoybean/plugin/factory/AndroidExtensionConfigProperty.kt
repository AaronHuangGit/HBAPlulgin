package com.hisoybean.plugin.factory

import com.android.build.gradle.BaseExtension
import com.hisoybean.plugin.HBAPlugin

abstract class AndroidExtensionConfigProperty<T>() : HBAConfigProperty<T> {
    protected fun getAndroidExtension(): BaseExtension? {
        return HBAPlugin.getAndroidExtension()
    }
}