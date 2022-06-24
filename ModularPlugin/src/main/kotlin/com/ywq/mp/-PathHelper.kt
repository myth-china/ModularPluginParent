package com.ywq.mp

import java.io.File

fun Module.getRealPath(rootDir: File): String {
    return getRealPath(rootDir.absolutePath)
}

fun Module.getRealPath(rootDir: String): String {
    var path = this.path!!
    return if (path.startsWith("/")) {
        Modular.get().rootPath + path
    } else {
        val pathSegments = rootDir.split("/").toMutableList()
        while (path.contains("../")) {
            path = path.replaceFirst("../", "")
            pathSegments.removeLast()
        }
        pathSegments.joinToString(separator = "/") + "/" + path
    }
}

fun Module.pathExist(rootDir: File): Boolean {
    return pathExist(rootDir.absolutePath)
}

fun Module.pathExist(rootDir: String): Boolean {
    val file = File(this.getRealPath(rootDir))

    if (!file.exists()) {
        println("#### Modular Plugin Error ####")
        println("Can not find $name project in path ${this.getRealPath(rootDir)}")
        println("#####")
        return false
    }

    return true
}