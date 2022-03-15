import koinToken from '../koin';
import { com, getMovieDetails } from '@terrapin/grass';

/**
 * Use this to create a quick alias to :water's `searchMovies` function.
 */
function getRetrievalFunction(): (id: number) => Promise<com.cliabhach.terrapin.net.filtered.movie.MovieDetails> {
  return function(id: number) {
    return getMovieDetails(koinToken, id);
  }
}

/**
 * Cast an 'Unusable' MovieDetails to the parent 'MovieDetails' type.
 *
 * We can't just cast the parameter, so this method uses a cast to
 * 'unknown' to satisfy the TypeScript type checker. Normally an import
 * alias like 'import Unusable = com....MovieDetails.Unusable' would be
 * enough to get some functional polymorphism, but the browser throws a
 * ReferenceError when that's done, so this will have to do.
 *
 * There must be some kind of transform in Webpack or tsc (the TypeScript
 * compiler) that breaks single namespace+class imports. Refer to the
 * module README for a more in-depth discussion.
 */
function castCorrectly(input: com.cliabhach.terrapin.net.filtered.movie.MovieDetails.Unusable): com.cliabhach.terrapin.net.filtered.movie.MovieDetails {
  return input as unknown as com.cliabhach.terrapin.net.filtered.movie.MovieDetails;
}

export { castCorrectly, getRetrievalFunction };