package com.hisoybean.plugin

import com.hisoybean.plugin.base.HBBaseExtension
import com.hisoybean.plugin.service.HBAConfigService

open class HBAExtension(private val hbaConfigService: HBAConfigService) : HBBaseExtension() {
    companion object {
        const val EXTENSION_NAME = "HBAConfig" //定义DSL名称（build.gradle文件中用来配置参数的）
        const val DEFAULT_VERSION_CODE = 1
        const val DEFAULT_VERSION_NAME = "1.0"
    }

    //配置android versionCode
    var versionCode: Int = DEFAULT_VERSION_CODE
        set(value) {
            field = value
            hbaConfigService.updateVersionCode(value)
        }

    //配置android VersionName
    var versionName: String = DEFAULT_VERSION_NAME
        set(value) {
            field = value
            hbaConfigService.updateVersionName(value)
        }

    //配置android ApplicationId
    var applicationId: String = ""
        set(value) {
            field = value
            hbaConfigService.updateApplicationId(value)
        }

    //配置是否需要maven发布
    var mavenPublish: Boolean = false
        set(value) {
            field = value
            hbaConfigService.updateMavenPublish(value)
        }

    //configurations.all{resolutionStrategy.cacheChangingModulesFor 0, 'seconds'}
    var syncWithCleanGradleCache: Boolean = false
        set(value) {
            field = value
            hbaConfigService.updateSyncWithCleanGradleCache(value)
        }
}