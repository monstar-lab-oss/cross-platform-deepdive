package com.cliabhach.terrapin.net.raw.movie

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * @author Philip Cohn-Cort
 */
@JsExport
@Serializable
data class SearchResult(
    val id: Int,
    val adult: Boolean,
    val overview: String?,
    val title: String?,
    val popularity: Double?,
    val video: Boolean,

)
