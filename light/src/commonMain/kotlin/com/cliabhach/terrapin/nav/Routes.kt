package com.cliabhach.terrapin.nav

/**
 * Constants for navigation.
 *
 * @author Philip Cohn-Cort
 */
object Routes {
    object home : Route("/")
    object search : Route("/search")
}

sealed class Route(val path: String)
