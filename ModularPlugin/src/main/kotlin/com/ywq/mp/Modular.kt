package com.ywq.mp

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.io.File
import java.io.FileInputStream

data class Modular(
    var debug: Boolean = true,
    var rootPath: String? = null,
    var moduleList: List<Module>? = null
) {
    companion object {
        private lateinit var modular: Modular

        fun init(yamlPath: String): Boolean {
            try {
                if (File(yamlPath).exists()) {
                    modular = Yaml(Constructor(Modular::class.java)).load(FileInputStream(yamlPath))
                    return true
                }
            } catch (e: Exception) {
                println("#### Modular Plugin Config File Format Error ####")
                return false
            }
            return false
        }

        fun get(): Modular {
            return modular
        }
    }
}