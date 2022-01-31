package com.cliabhach.terrapin.net.filtered.config

/**
 * Top-level configuration info about the API.
 *
 * Use this to create image urls and so forth. See [ImageUrls] for
 * further details. Counterpart to [com.cliabhach.terrapin.net.raw.config.Root].
 *
 * @author Philip Cohn-Cort
 */
sealed class ApiConfig {

    data class Unknown(
        val message: String
    ) : ApiConfig()

    data class Known(
        val images: ImageUrls
    ): ApiConfig()
}
