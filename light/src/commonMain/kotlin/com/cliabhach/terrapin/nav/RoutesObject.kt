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

    // NB: This object is not exported correctly by Kotlin/JS 1.6.20-M1
    // To use it from TypeScript code, edit the exported .d.ts file to
    // read `class RoutesObject {` instead of `const RoutesObject: {`.

    object Home : RouteObject("/")
    object Search : RouteObject("/search")
}

@JsExport
val TimeA = RoutesObject.Home

@JsExport
sealed class RouteObject(val path: String)
