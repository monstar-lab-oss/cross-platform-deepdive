import com.cliabhach.terrapin.di.netModule
import com.cliabhach.terrapin.net.Api
import com.cliabhach.terrapin.net.filtered.movie.MovieDetails
import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.promise
import org.koin.core.context.startKoin
import org.koin.core.Koin
import kotlin.js.Promise

/**
 * Search the Movie DB for a particular string of text.
 */
@JsExport
fun searchMovies(
    koinToken: KoinToken,
    query: String
): Promise<SearchResultsPage> {
    return CoroutineScope(Dispatchers.Default).promise {
        koinToken.getFromKoin(Api::class).searchMovies(query)
    }
}

/**
 * Retrieve a specific Movie from the Movie DB, given its unique id.
 */
@JsExport
fun getMovieDetails(
    koinToken: KoinToken,
    id: Int
): Promise<MovieDetails> {
    return CoroutineScope(Dispatchers.Default).promise {
        koinToken.getFromKoin(Api::class).getMovieDetails(id)
    }
}

/**
 * Prepare our Dependency-Injection tool [Koin] for use.
 *
 * Call this once when your program is starting up (in index.html or
 * similar) to ensure Koin is available too. Some method calls in
 * :water won't work unless Koin has been started in this way. We
 * return a [KoinToken] that must be provided to those method calls.
 *
 * See [searchMovies] for an example of such a method call.
 */
@JsExport
fun insertKoin(): KoinToken {
    return KoinToken(startKoin {
        modules(netModule, tsModule)
    })
}

