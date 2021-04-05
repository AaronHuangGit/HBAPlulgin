package com.hisoybean.plugin.factory.property

import com.hisoybean.plugin.HBAExtension
import com.hisoybean.plugin.HBAPlugin
import com.hisoybean.plugin.factory.AndroidExtensionConfigProperty

class VersionName :
    AndroidExtensionConfigProperty<String>() {
    override var value: String? = HBAExtension.DEFAULT_VERSION_NAME
        set(value) {
            field = value
            getAndroidExtension()?.apply {
                defaultConfig.apply {
                    versionName = value
                }
            }
        }
}