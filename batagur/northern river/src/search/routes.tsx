import './styles.css';
import React, { ChangeEvent, Component, Dispatch, ReactNode, useState } from 'react';
import { getSearchFunction, SearchResultsPage } from './net';

/**
 * Properties for a SearchBox.
 *
 * Currently contains nothing, since we don't have anything that needs
 * to be configured that way.
 */
interface SearchProps {
  readonly onResultsFound: Dispatch<typeof SearchResultsPage>
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
    this.notifyQueryResults = props.onResultsFound;
  }

  private searchMovies = getSearchFunction()
  private notifyQueryResults: Dispatch<typeof SearchResultsPage>;

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
      this.notifyQueryResults(resultsPage);
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

/**
 * Default layout for the search screen.
 *
 * This is basically just a div with a [h1] and a [SearchBox].
 */
export default class SearchRoute extends Component<SearchProps> {

  // TODO: convert this into a class so we can use props?
  constructor(props: SearchProps) {
    super(props);
    this.setSearchResults = props.onResultsFound;
  }

  private setSearchResults: Dispatch<typeof SearchResultsPage>;

  render(): React.ReactNode {
    return (
      <main style={{ color: "lightgreen" }}>
        <h1>The search screen!</h1>
        <SearchBox onResultsFound={this.setSearchResults}>
        </SearchBox>
      </main>
    );
  }
}