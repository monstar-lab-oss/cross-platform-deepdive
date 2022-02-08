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