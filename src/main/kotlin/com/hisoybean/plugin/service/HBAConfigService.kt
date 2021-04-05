package com.hisoybean.plugin.service

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.DefaultConfig
import com.hisoybean.plugin.constants.Plugins
import org.gradle.api.Project
import java.util.concurrent.TimeUnit

class HBAConfigService(private val project: Project, private val androidExtension: BaseExtension?) {

    fun updateVersionCode(value: Int) {
        updateDefaultConfig {
            versionCode = value
        }
    }

    fun updateVersionName(value: String) {
        updateDefaultConfig {
            versionName = value
        }
    }

    fun updateApplicationId(value: String) {
        updateDefaultConfig {
            applicationId = value
        }
    }

    fun updateMavenPublish(value: Boolean) {
        if (!value) return
        project.plugins.apply(Plugins.MAVEN)
    }

    fun updateSyncWithCleanGradleCache(value: Boolean) {
        if (!value) return
        project.configurations.all {
            it.resolutionStrategy.cacheDynamicVersionsFor(0, TimeUnit.SECONDS)
            it.resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
        }
    }


    private inline fun updateDefaultConfig(block: DefaultConfig.() -> Unit) {
        androidExtension?.apply {
            defaultConfig.apply {
                block()
            }
        }
    }


}