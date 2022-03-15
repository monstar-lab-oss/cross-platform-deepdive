import koinToken from '../koin';
import { com, searchMovies } from '@terrapin/grass';

/**
 * Use this to create a quick alias to :water's `searchMovies` function.
 */
function getSearchFunction(): (query: string) => Promise<com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage> {
  return function(query: string) {
    return searchMovies(koinToken, query);
  }
}

export { getSearchFunction };