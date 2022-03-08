import koinToken from '../koin';
import { com, searchMovies } from '@terrapin/water';
const SearchResultsPage = com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage;

/**
 * Use this to create a quick alias to :water's `searchMovies` function.
 */
function getSearchFunction(): (query: string) => Promise<typeof SearchResultsPage> {
  return function(query: string) {
    return searchMovies(koinToken, query);
  }
}

export { SearchResultsPage };
export { getSearchFunction };