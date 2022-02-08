package com.cliabhach.terrapin.nav

import kotlin.js.JsExport

/**
 * Variation of the 'Route' concept with Data Classes.
 *
 * Note that you can't use a Sealed Class as the parameter
 * type for a constructor to a Data Class. There's also a
 * bunch of other restrictions, which you can find in the
 * official docs, but I found the Sealed Class one rather
 * interesting.
 *
 * @author Philip Cohn-Cort
 */
@JsExport
data class RoutesDataClass(
    val where: String = "??",
    val home: RouteDataClass = defHome,
    val search: RouteDataClass = defSearch
) {
    companion object {
        val defHome = RouteDataClass("/")
        val defSearch = RouteDataClass("/search")
    }
}

@JsExport
data class RouteDataClass(val path: String)