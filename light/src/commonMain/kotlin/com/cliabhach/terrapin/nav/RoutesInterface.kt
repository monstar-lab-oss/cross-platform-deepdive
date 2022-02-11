package com.cliabhach.terrapin.nav

import kotlin.js.JsExport

/**
 * Variation of the 'Route' concept with an interface and abstract class.
 *
 * Interfaces can't be exported directly into JS, but they do exist in
 * TypeScript. Curious. I've marked the properties and class as private
 * to make sure they don't appear in the TS definition file, where the
 * mismatch causes `tsc` compilation failure TS2724.
 *
 * @author Philip Cohn-Cort
 */
@JsExport
open class RoutesInterface {
    val where = "??"

    /**
     * NB: With Kotlin 1.6.10, this object is not visible to JS code.
     *
     * Kotlin 1.6.20-M1 fixes that and makes it visible.
     */
    companion object {
        val another = "50"
    }

    private object home : RouteInterface {
        override val path: String
            get() = "/"
    }

    private object search : RouteInterface {
        override val path: String = "/search"
    }
}

private interface RouteInterface {
    val path: String
}