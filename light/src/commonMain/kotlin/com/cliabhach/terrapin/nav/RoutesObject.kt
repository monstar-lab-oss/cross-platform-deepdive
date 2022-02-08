package com.cliabhach.terrapin.nav

import kotlin.js.JsExport

/**
 * Variation of 'Routes' that uses 'object' syntax.
 *
 * A Route is a constant used for navigation.
 *
 * @author Philip Cohn-Cort
 */
@JsExport
object RoutesObject {
    val where = "??"

    object home : RouteObject("/")
    object search : RouteObject("/search")
}

@JsExport
sealed class RouteObject(val path: String)
