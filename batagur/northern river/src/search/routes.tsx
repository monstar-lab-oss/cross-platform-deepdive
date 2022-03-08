import './styles.css';
import React, { ChangeEvent, Component, ReactNode } from 'react';
import { getSearchFunction, SearchResultsPage } from './net';

class SearchBox extends Component {
  constructor(props: any) {
    super(props)

    this.updateQuery = this.updateQuery.bind(this);
    this.sendQuery = this.sendQuery.bind(this);
  }

  private searchMovies = getSearchFunction()

  state = {
    query: ""
  }

  updateQuery(event: ChangeEvent<HTMLInputElement>) {
    this.setState({ query: event.target.value})
  }

  sendQuery(event: React.MouseEvent) {
    // TODO: Use water
    this.searchMovies(this.state.query).then((resultsPage: typeof SearchResultsPage) => {
      if (resultsPage instanceof SearchResultsPage.Unusable) {
        console.log("oh no " + resultsPage.message);
      } else if (resultsPage instanceof SearchResultsPage.Results) {
        console.log("found results! " + resultsPage.items);
      }
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