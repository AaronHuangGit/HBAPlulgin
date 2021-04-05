package com.hisoybean.plugin.factory.property

import com.hisoybean.plugin.factory.AndroidExtensionConfigProperty

class ApplicationId :
    AndroidExtensionConfigProperty<String>() {
    override var value: String? = null
        set(value) {
            getAndroidExtension()?.apply {
                defaultConfig.apply {
                    (value as? String)?.apply {
                        applicationId = this
                    }
                }
            }
            field = value
        }

}