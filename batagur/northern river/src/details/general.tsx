import { com, createDetails } from '@terrapin/grass';
const MovieDetails = com.cliabhach.terrapin.net.filtered.movie.MovieDetails;

const noMovieDetails = new MovieDetails.Unusable(0, "No content found yet.");

export { MovieDetails, noMovieDetails, createDetails };