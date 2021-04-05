package com.hisoybean.plugin

import com.android.build.gradle.BaseExtension
import com.hisoybean.plugin.base.HBBasePlugin
import com.hisoybean.plugin.constants.AndroidCommonDeps
import com.hisoybean.plugin.constants.AndroidExtensionConstants
import com.hisoybean.plugin.constants.Plugins
import com.hisoybean.plugin.constants.Utils
import com.hisoybean.plugin.tasks.HBATask
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

class HBAPlugin : HBBasePlugin<HBAExtension>() {
    companion object {
        private const val ANDROID_EXTENSION_NAME = "android"
        private const val IMPLEMENTATION_NAME = "implementation"
    }

    private lateinit var projectPath: String
    private var androidExtension: BaseExtension? = null
//    private val configChangedCallback = object : HBAConfigChangedCallback<HBAConfigProperty> {
//        override fun onChange(hbaConfigProperty: HBAConfigProperty, value: Any) {
//            hbaConfigProperty.apply(value)
//        }

//        override fun onChange(value: Any) {
//            Utils.printlnLine()
//            println("apply HBAConfig to AndroidExtension: $propertyName -> $value")
//            when (propertyName) {
//                PropertyName.APPLICATION_ID -> {
//                    androidExtension?.apply {
//                        defaultConfig.apply {
//                            (value as? String)?.apply {
//                                applicationId = this
//                            }
//                        }
//                    }
//                }
//                PropertyName.VERSION_CODE -> {
//                    androidExtension?.apply {
//                        defaultConfig.apply {
//                            (value as? Int)?.apply {
//                                versionCode = this
//                            }
//                        }
//                    }
//                }
//                PropertyName.VERSION_NAME -> {
//                    androidExtension?.apply {
//                        defaultConfig.apply {
//                            (value as? String)?.apply {
//                                versionName = this
//                            }
//                        }
//                    }
//                }
//                PropertyName.MAVEN_PUBLISH -> {
//                    sProject.plugins.apply(Plugins.MAVEN)
//                }
//                PropertyName.SYNC_WITH_CLEAN_GRADLE_CACHE -> {
//
//                }
//                else -> return
//            }
//        }
//    }

    override fun apply(project: Project) {
        super.apply(project)
        init(project)
    }

    private fun init(project: Project) {
        Utils.printlnLine()
        println("****start HBAPlugin configure****")
        initData()
        applyCommonPlugins()
        modifyAndroidBaseConfig()
        addCommonDependencies()
//        createTaskExample(project)
        doSomethingAfterEvaluate()
        Utils.printlnLine()
        println("****apply HBAPlugin configure finished****")
    }

    override fun getExtensionClass(): Class<out HBAExtension> {
        return HBAExtension::class.java
    }

    override fun createExtension(): HBAExtension? {
        androidExtension = getAndroidExtension()
        androidExtension ?: return null
        return project.extensions.create(HBAExtension.EXTENSION_NAME, getExtensionClass())
    }

    private fun initData() {
        projectPath = project.projectDir.absolutePath
    }

    //应用公共插件
    private fun applyCommonPlugins() {
        Utils.printlnLine()
        println("**applyCommonPlugins(): apply common plugins**")
        project.plugins.apply {
            apply(Plugins.KOTLIN)
        }
    }

    //Android基础配置
    private fun modifyAndroidBaseConfig() {
        Utils.printlnLine()
        println(
            "**modifyAndroidBaseConfig(): modification android base config" +
                    "(include compileSdkVersion, minSdkVersion...)**"
        )
        androidExtension?.apply {
            compileSdkVersion(AndroidExtensionConstants.compileSdkVersion)
            defaultConfig {
                it.minSdkVersion(AndroidExtensionConstants.minSdkVersion)
                it.testInstrumentationRunner(AndroidExtensionConstants.testInstrumentationRunner)
            }
            compileOptions {
                it.sourceCompatibility = AndroidExtensionConstants.compileOptionsJavaVersion
                it.targetCompatibility = AndroidExtensionConstants.compileOptionsJavaVersion
            }
            addSigningConfigs()
            configureBuildTypes()
            project.tasks.withType(KotlinCompile::class.java).configureEach {
                it.kotlinOptions {
                    jvmTarget = AndroidExtensionConstants.kotlinOptionJvmTarget
                }
            }
        }
    }

    //添加公共依赖
    private fun addCommonDependencies() {
        Utils.printlnLine()
        println(
            "**addCommonDependencies(): add Common Android Dependencies" +
                    "(include androidx, appCompat,test.....)**"
        )
        val implementDeps = mutableListOf(
            AndroidCommonDeps.kotlinStdlib,
            AndroidCommonDeps.androidXCore,
            AndroidCommonDeps.appCompat,
            AndroidCommonDeps.androidMaterial
        )
        project.dependencies.apply {
            implementDeps.forEach {
                addImplementationInDeps(this, it)
                println("implementation $it")
            }
            add("testImplementation", "junit:junit:4.+")
            add("androidTestImplementation", "androidx.test.ext:junit:1.1.2")
            add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.3.0")
        }
    }

    //添加Android签名SigningConfig配置
    private fun BaseExtension.addSigningConfigs() {
        signingConfigs.create("HBASignConfig") {
            it.storeFile(File("$projectPath/yixue.ks"))
            println("signingConfig file path: ${it.storeFile?.absolutePath}")
            it.storePassword("Yixue123")
            it.keyAlias("yixue")
            it.keyPassword("Yixue123")
        }
    }

    //配置默认的buildType，也可以使用buildTypes.create进行添加
    private fun BaseExtension.configureBuildTypes() {
        buildTypes.configureEach {
            println("buildType name: ${it.name}")
            it.signingConfig = signingConfigs.getByName("HBASignConfig")
            it.proguardFiles = mutableListOf(File("proguard-rules.pro"))
            if (it.name == "release") {
                it.minifyEnabled(true)
                it.isShrinkResources = true
            }
        }
    }

    //project配置完成后执行
    private fun doSomethingAfterEvaluate() {
        project.afterEvaluate {
            Utils.printlnLine()
            println(
                "afterEvaluate(callback for gradle configure finished)：" +
                        "hbaExtension.versionCode: ${hbBaseExtension?.versionCode}( Verification code)"
            )
            Utils.printlnLine()
        }
    }

    //添加Task示例
    private fun createTaskExample(project: Project) {
        val task = project.tasks.register("HBATask", HBATask::class.java)
        val preBuildTask = project.tasks.findByName("preBuild")
        if (preBuildTask == null) {
            println("preBuild is null")
        } else {
            println("find preBuild")
            preBuildTask.dependsOn("HBATask")
        }
    }

    private fun addImplementationInDeps(dependencyHandler: DependencyHandler?, depName: String?) {
        dependencyHandler?.let {
            depName?.let {
                dependencyHandler.add(IMPLEMENTATION_NAME, depName)
            }
        }
    }

    private fun getAndroidExtension(): BaseExtension? {
        return project.extensions.findByName(ANDROID_EXTENSION_NAME) as? BaseExtension
    }

}