package com.ywq.mp

data class Module(
    var debug: Boolean = false,
    var name: String? = null,
    var group: String? = null,
    var version: String? = null,
    var path: String? = null,
    //substitute/includeBuild
    var mode: String? = MODE_INCLUDE_BUILD
) {
    companion object {
        const val MODE_INCLUDE_BUILD = "includeBuild"
        const val MODE_SUBSTITUTE = "substitute"
    }
}