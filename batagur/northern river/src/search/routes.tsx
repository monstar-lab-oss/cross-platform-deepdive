import './styles.css';
import React, { ChangeEvent, Component, ReactNode } from 'react';
import { getSearchFunction, SearchResultsPage } from './net';

/**
 * Properties for a SearchBox.
 *
 * Currently contains nothing, since we don't have anything that needs
 * to be configured that way.
 */
interface SearchProps {
}

/**
 * State for a SearchBox.
 *
 * Contains a [query], which represents whatever text is currently in
 * the input itself. We also track the search results themselves as
 * [items].
 */
interface SearchState {
  readonly query: string
  readonly searchResults: typeof SearchResultsPage.Empty
}

/**
 * A nice little input+button combination.
 *
 * Use this to search for movies.
 */
class SearchBox extends Component<SearchProps, SearchState> {
  constructor(props: SearchProps) {
    super(props)

    this.updateQuery = this.updateQuery.bind(this);
    this.sendQuery = this.sendQuery.bind(this);
  }

  private searchMovies = getSearchFunction()

  state: SearchState = {
    query: "",
    searchResults: SearchResultsPage.Empty
  }

  updateQuery(event: ChangeEvent<HTMLInputElement>) {
    this.setState({ query: event.target.value})
  }

  sendQuery(event: React.MouseEvent) {
    this.searchMovies(this.state.query).then((resultsPage: typeof SearchResultsPage) => {
      if (resultsPage instanceof SearchResultsPage.Unusable) {
        console.log("oh no " + resultsPage.message);
      } else if (resultsPage instanceof SearchResultsPage.Results) {
        console.log("found results! " + resultsPage.items[0]);
      }
      this.setState({ searchResults: resultsPage});
    })
  }

  render(): ReactNode {
    return <div className="searchbox">
      <label htmlFor="q">Search</label>
      <input
        id="q"
        type="search"
        placeholder="A Wrinkle In Time"
        onChange={this.updateQuery}
      />
      <button
        id="magnifying-glass"
        type="submit"
        className="icon icon_edit-find"
        value=""
        onClick={this.sendQuery}
      />
    </div>
  }
}

export default function SearchRoute() {
  return (
    <main style={{ color: "lightgreen" }}>
      <h1>The search screen!</h1>
      <SearchBox>
      </SearchBox>
    </main>
  );
}