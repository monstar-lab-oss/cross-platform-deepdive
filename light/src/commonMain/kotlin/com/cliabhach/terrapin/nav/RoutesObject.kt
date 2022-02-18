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

    object Home : RouteObject("/")
    object Search : RouteObject("/search")
}

@JsExport
val TimeA = RoutesObject.Home

@JsExport
sealed class RouteObject(val path: String)
