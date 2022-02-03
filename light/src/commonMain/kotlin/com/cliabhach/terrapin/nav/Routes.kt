package com.cliabhach.terrapin.nav

import kotlin.js.JsExport

/**
 * Constants for navigation.
 *
 * @author Philip Cohn-Cort
 */
@JsExport
object Routes {
    object home : Route("/")
    object search : Route("/search")
}

@JsExport
sealed class Route(val path: String)
