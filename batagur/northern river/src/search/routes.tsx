import './styles.css';

export default function SearchRoute() {
  return (
    <main style={{ color: "lightgreen" }}>
      <h1>The search screen!</h1>
      <div className="searchbox">
        <label htmlFor="q">Search</label>
        <input id="q" type="search" placeholder="A Wrinkle In Time"/>
        <button id="magnifying-glass" type="submit" value=""/>
      </div>
    </main>
  );
}