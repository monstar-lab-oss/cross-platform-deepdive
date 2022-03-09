import com.cliabhach.terrapin.di.HttpClientPlugin
import io.ktor.client.features.cache.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.core.Koin

/**
 * A [Koin] module to provide all TypeScript-specific values that DI might need.
 *
 * Note that Koin modules can't be declared directly in TS or JS code.
 */
val tsModule = module {
    single {
        listOf<HttpClientPlugin>(
            HttpCache
        )
    }
}