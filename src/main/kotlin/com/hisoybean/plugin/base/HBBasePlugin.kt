package com.hisoybean.plugin.base

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class HBBasePlugin<T: HBBaseExtension> : Plugin<Project> {
    protected lateinit var project: Project
    protected var hbBaseExtension: T? = null
    override fun apply(project: Project) {
        this.project = project
        hbBaseExtension = createExtension()
    }

    abstract fun getExtensionClass(): Class<out T>

    abstract fun createExtension(): T?

}