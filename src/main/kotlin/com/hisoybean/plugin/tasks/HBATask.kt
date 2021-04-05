package com.hisoybean.plugin.tasks

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