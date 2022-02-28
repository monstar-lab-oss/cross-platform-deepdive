import './styles.css';
import { Component, ReactNode } from 'react';

class SearchBox extends Component {
  // TODO: Add support for onChange and onClick events
  state = {
    query: ""
  }

  render(): ReactNode {
    return <div className="searchbox">
      <label htmlFor="q">Search</label>
      <input id="q" type="search" placeholder="A Wrinkle In Time" />
      <button id="magnifying-glass" type="submit" className="icon icon_edit-find" value="" />
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