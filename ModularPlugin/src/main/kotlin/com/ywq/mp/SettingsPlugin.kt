package com.ywq.mp

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import java.io.File

class SettingsPlugin : Plugin<Settings> {
    override fun apply(target: Settings) {
        val yamlPath = target.rootDir.absolutePath + File.separator + "modular.yml"
        if (Modular.init(yamlPath).not()) return

        val modular = Modular.get()
        println("#### Modular Plugin Config ####")
        println("debug: ${modular.debug}")
        println("rootPath: ${modular.rootPath}")
        println(target.rootDir)
        modular.moduleList?.forEach {
            println(it)
        }
        println("####")

        if (!Modular.get().debug) {
            return
        }


        Modular.get().moduleList?.forEach {
            if (it.debug) {

                val name = it.name!!

                if (!it.pathExist(target.rootDir)) return@forEach

                if (it.mode == Module.MODE_INCLUDE_BUILD) {
                    target.includeBuild(it.getRealPath(target.rootDir))
                } else if (it.mode == Module.MODE_SUBSTITUTE) {
                    target.include(":$name")
                    target.project(":$name").projectDir = File(it.getRealPath(target.rootDir))
                }
            }
        }
    }
}