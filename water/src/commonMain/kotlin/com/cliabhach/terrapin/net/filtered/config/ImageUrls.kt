package com.cliabhach.terrapin.net.filtered.config

import com.cliabhach.terrapin.net.raw.config.Images

/**
 * Use this when creating api calls to fetch images from the API.
 */
data class ImageUrls(
    val images: Images,
) {

    /**
     * Create a base url for downloading 'poster' images.
     *
     * Poster sizes are defined as `w` followed by a number of pixels.
     * Example: "w92", "w185", etc. There will always be an "original"
     * size too. We might have received any number of sizes from the
     * API, so this method tries its best to choose a workable
     * approximation of the requested size.
     */
    fun getBasePosterUrl(size: Int): String {
        val specificSizes = images.posterSizes?.filter {
            it.startsWith('w')
        } ?: emptyList()

        val bestSize = specificSizes.map {
            it.substringAfter('w').toInt()
        }.asReversed().firstOrNull { it <= size }

        // While there _is_ always an original size, don't request it here - that
        // would be an unnecessary strain on the API.
        //
        // TODO: Perhaps return an empty string altogether if there are no sizes?
        val sizeString = "w${bestSize ?: 0}"

        // baseUrl ends in '/', paths start with '/'
        return "${images.baseUrl}${sizeString}"
    }
}
