import { Dispatch, SetStateAction } from "react";
import { com } from '@terrapin/grass';
import { noMovieDetails, obtainUnusableDetails, MovieDetailsPromise } from "../details/general";
import { castCorrectly, getRetrievalFunction } from "../details/net";

const retrievalFunction = getRetrievalFunction();

/**
 * Create a corresponding MovieDetails object, given a `SearchResultsPage`.
 *
 * Unusable `SearchResultsPage`s are converted into unusable MovieDetails,
 * but we send off the first item on each usable page to the API to get some
 * real data.
 * @param page any page of search results; c.f. the `SearchBox` component
 * @returns a (probably-new) MovieDetails object reflecting that page
 */
async function detailsFromResult(page: com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage): MovieDetailsPromise {
  let details: MovieDetailsPromise;

  if (page instanceof com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage.Unusable) {
    details = Promise.resolve(obtainUnusableDetails(page.message)).then(castCorrectly);
  } else if (page instanceof com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage.Results) {
    details = retrievalFunction(page.items[0].id);
  } else {
    details = Promise.resolve(noMovieDetails).then(castCorrectly);
  }

  return details;
}

/**
 * Synchronous function for invoking `detailsFromResult`.
 * @param page any page of search results; c.f. the `SearchBox` component
 * @param setMovieDetails a callback function, such as one created by `React.useState`
 */
export function linkDetails(page: com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage, setMovieDetails: Dispatch<SetStateAction<com.cliabhach.terrapin.net.filtered.movie.MovieDetails>>) {
  detailsFromResult(page).then(setMovieDetails);
}