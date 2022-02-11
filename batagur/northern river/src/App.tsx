import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomeRoute from './home/routes';
import SearchRoute from './search/routes';
import LightRoutes from './routes';

export default function App() {
  return (
    <div className="App">
      <header className="App-header">
        <BrowserRouter>
          <Routes>
            <Route path={LightRoutes.Search.path} element={<SearchRoute />} />
            <Route path={LightRoutes.Home.path} element={<HomeRoute />} />
          </Routes>
        </BrowserRouter>
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className="App-link"
          href={LightRoutes.search.path}
          rel="noopener noreferrer"
        >
          Switch to Search
        </a>
      </header>
    </div>
  );
}
