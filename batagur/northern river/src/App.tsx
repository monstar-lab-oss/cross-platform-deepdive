import { useState } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { com, createDetails } from '@terrapin/grass';
import SearchRoute from './search/routes';
import HomeRoute from './home/routes';
import { linkDetails } from './home/linkDetails';
import { noMovieDetails } from './details/general';
import { castCorrectly } from './details/net';
import LightRoutes from './routes';
import './App.css';

export default function App() {

  const [movieDetails, setMovieDetails] = useState(castCorrectly(noMovieDetails));
  const retrieveDetails = (page: com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage) => {
    linkDetails(page, setMovieDetails)
  };

  return (
    <div className="App">
      <header className="App-header">
        <BrowserRouter>
          <Routes>
            <Route path={LightRoutes.Search.path} element={<SearchRoute onResultsFound={retrieveDetails} />} />
            <Route path={LightRoutes.Home.path} element={<HomeRoute />} />
          </Routes>
        </BrowserRouter>
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        {createDetails(movieDetails)}
        <a
          className="App-link"
          href={LightRoutes.Search.path}
          rel="noopener noreferrer"
        >
          Switch to Search
        </a>
      </header>
    </div>
  );
}
