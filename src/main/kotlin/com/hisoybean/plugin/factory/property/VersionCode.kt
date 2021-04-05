package com.hisoybean.plugin.factory.property

import com.hisoybean.plugin.HBAExtension
import com.hisoybean.plugin.factory.AndroidExtensionConfigProperty

class VersionCode :
    AndroidExtensionConfigProperty<Int>() {
    override var value: Int? = HBAExtension.DEFAULT_VERSION_CODE
        set(value) {
            getAndroidExtension()?.apply {
                defaultConfig.apply {
                    (value as? Int)?.apply {
                        versionCode = this
                    }
                }
            }
            field = value
        }
}