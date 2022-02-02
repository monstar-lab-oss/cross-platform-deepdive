import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomeRoute from './home/routes';
import SearchRoute from './search/routes';

export default function App() {
  return (
    <div className="App">
      <header className="App-header">
        <BrowserRouter>
          <Routes>
            <Route path="/search" element={<SearchRoute />} />
            <Route path="/" element={<HomeRoute />} />
          </Routes>
        </BrowserRouter>
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
};
