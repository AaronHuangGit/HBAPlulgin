package com.hisoybean.plugin.tasks

import com.android.build.gradle.BaseExtension
import com.hisoybean.plugin.HBAExtension
import com.hisoybean.plugin.constants.Plugins
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class HBATask : DefaultTask() {

    companion object {
        const val LOG_HEAD = "HBATask: "
    }

    @TaskAction
    fun execute() {
        println()
    }

}