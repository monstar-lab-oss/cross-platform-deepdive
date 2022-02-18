@file:Suppress("ArrayInDataClass")

package com.cliabhach.terrapin.net.raw.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 */
@JsExport
@Serializable
data class Images(
    @SerialName("base_url")
    val baseUrl: String? = null,
    @SerialName("secure_base_url")
    val secureBaseUrl: String? = null,
    @SerialName("backdrop_sizes")
    val backdropSizes: Array<String>? = null,
    @SerialName("logo_sizes")
    val logoSizes: Array<String>? = null,
    @SerialName("poster_sizes")
    val posterSizes: Array<String>? = null,
    @SerialName("profile_sizes")
    val profileSizes: Array<String>? = null,
    @SerialName("still_sizes")
    val stillSizes: Array<String>? = null,
): Configuration