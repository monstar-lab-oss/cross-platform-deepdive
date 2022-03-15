import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomeRoute from './home/routes';
import SearchRoute from './search/routes';
import LightRoutes from './routes';
import { com, createDetails } from '@terrapin/grass';
import { useState } from 'react';
import { noMovieDetails } from './details/general';
import { castCorrectly } from './details/net';

export default function App() {

  // TODO: connect setter to a Dispatch in SearchRoute via getMovieDetails
  const [movieDetails, setMovieDetails] = useState(castCorrectly(noMovieDetails));
  const retrieveDetails = (page: com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage) => { movieDetails };

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
        { createDetails(movieDetails) }
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
