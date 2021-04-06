package com.hisoybean.plugin.service

import com.android.build.api.component.Component
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.DefaultConfig
import com.hisoybean.plugin.constants.Plugins
import org.gradle.api.Project
import org.gradle.api.component.SoftwareComponent
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
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
        buildAutoMavenPublish()
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

    //TODO 创建maven自动发布到仓库
    private fun buildAutoMavenPublish() {
//        project.afterEvaluate {
//            val publishExtension = it.extensions.findByName("publishing") as? PublishingExtension
//            println("publishExtension: $publishExtension")
//            publishExtension?.publications?.create("release", MavenPublication::class.java)?.apply {
//                    from(project.components.getByName("release"))
//                    groupId =
//            }
//        }
    }
}