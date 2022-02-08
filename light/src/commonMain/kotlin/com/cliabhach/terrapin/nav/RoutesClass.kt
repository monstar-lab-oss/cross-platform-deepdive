package com.cliabhach.terrapin.nav

import kotlin.js.JsExport

/**
 * Variation of the 'Route' concept with final classes.
 *
 * As far as Kotlin goes, this is probably the variation with
 * the fewest distinct concepts.
 *
 * @author Philip Cohn-Cort
 */
@JsExport
class RoutesClass {
    val where = "??"

    val home = RouteClass("/")
    val search = RouteClass("/search")
}

@JsExport
class RouteClass(val path: String)