package com.cliabhach.terrapin.net.raw.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The root of the configuration tree - this represents part of the API.
 *
 * Counterpart to [com.cliabhach.terrapin.net.filtered.config.ApiConfig]
 *
 * @author Philip Cohn-Cort
 */
@Serializable
data class Root(
    val images: Images,
    @SerialName("change_keys")
    val changeKeys: Array<String>,
): Configuration