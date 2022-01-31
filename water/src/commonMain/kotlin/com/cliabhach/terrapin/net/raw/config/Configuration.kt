package com.cliabhach.terrapin.net.raw.config

/**
 * Marker annotation for configuration objects.
 *
 * The Movie DB API might mark some properties as optional, but this codebase
 * doesn't care about that. The de-serializer can handle that.
 *
 * C.f. [https://developers.themoviedb.org/3/configuration/get-api-configuration]
 *
 * @author Philip Cohn-Cort
 */
interface Configuration