package com.ywq.mp

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class ProjectPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val yamlPath = target.rootDir.absolutePath + File.separator + "modular.yml"
        if (Modular.init(yamlPath).not()) return

        if (Modular.get().debug.not()) {
            return
        }

        Modular.get().moduleList?.forEach { m ->
            if (m.debug) {
                target.subprojects { project ->
                    substitute(project, m)
                }
            }
        }
    }

    private fun substitute(project: Project, m: Module) {
        if (m.mode == Module.MODE_INCLUDE_BUILD) {
            return
        }

        if (!m.pathExist(project.rootDir)) return

        project.configurations.all {
            it.resolutionStrategy.dependencySubstitution { ds ->
                project.project(":${m.name!!}").file(m.getRealPath(project.rootDir))
                ds.substitute(ds.module("${m.group!!}:${m.name!!}")).using(ds.project(":${m.name!!}"))
            }
        }
    }
}