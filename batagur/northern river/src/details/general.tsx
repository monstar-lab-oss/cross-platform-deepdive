// NB: This file is particularly sensitive to mismatching imports, such as
// importing 'com' from :water and 'createDetails' from :grass.
//
// If we try that, the kotlin code throws a NoWhenBranchMatchedException.
import { com, createDetails } from '@terrapin/grass';

/**
 * Helper function for creating new 'MovieDetails.Unusable' objects.
 *
 * For default values, we only need a message - use this method instead of
 * that class's constructor to avoid needing to consider the 'id' parameter.
 * @param message a user-friendly explanation of why the details are unusable
 * @returns a new 'MovieDetails.Unusable' object
 */
function obtainUnusableDetails(message: string): com.cliabhach.terrapin.net.filtered.movie.MovieDetails.Unusable {
  return new com.cliabhach.terrapin.net.filtered.movie.MovieDetails.Unusable(0, message);
}

const noMovieDetails = obtainUnusableDetails("No content found yet.");

type MovieDetailsPromise = Promise<com.cliabhach.terrapin.net.filtered.movie.MovieDetails>;

export type { MovieDetailsPromise };
export { noMovieDetails, obtainUnusableDetails, createDetails };