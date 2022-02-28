import './styles.css';
import React, { ChangeEvent, Component, ReactNode } from 'react';

class SearchBox extends Component {
  state = {
    query: ""
  }

  updateQuery(event: ChangeEvent<HTMLInputElement>) {
    this.setState({ query: event.target.value})
  }

  sendQuery(event: React.MouseEvent) {
    // TODO: Use water
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