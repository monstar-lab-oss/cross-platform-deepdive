import io.ktor.client.*
import org.koin.core.KoinApplication
import kotlin.reflect.KClass

/**
 * JavaScript and TypeScript code can't use Koin directly, but they can use this class.
 *
 * Register modules and other injectable values in [tsModule], and retrieve whatever you
 * need with [getFromKoin].
 *
 * @see insertKoin
 */
@JsExport
class KoinToken internal constructor(private val application: KoinApplication) {

    val httpClient: HttpClient
        get() {
            return getFromKoin(HttpClient::class)
        }

    internal fun <T : Any> getFromKoin(type: KClass<T>): T {
        return application.koin.get(type, null, null)
    }
}