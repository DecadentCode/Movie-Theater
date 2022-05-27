import { useEffect, useState } from "react";
import MovieObject from "../components/MovieObject";
import Tickets from "../components/Tickets";
import Movie from "../models/Movie";
import { getTrendingMovies } from "../services/MovieService";
import "./HomeRoute.css";

const HomeRoute = () => {
  const [movies, setMovies] = useState<Movie[]>([]);

  useEffect(() => {
    getTrendingMovies().then((response) => {
      setMovies(response.results);
    });

    // fetch("/api/movies")
    //   .then((res) => res.json())
    //   .then((data) => setMovies(data));
  }, []);

  return (
    <div className="HomeRoute">
      <h1>Welcome to Colonial Drive-In Theatre!</h1>
      <p>
        We are a small, family-owned, community-based movie theater. Now 80%
        blob free!
      </p>
      <div className="HomeCompContainer">
        <h2>MERCH</h2>
      </div>
      <div className="HomeCompContainer">
        <h2>NOW PLAYING</h2>
        <ul>
          {movies.map((item) => (
            <li key={item.id}>
              <MovieObject movie={item} />
              <Tickets movie={item} />
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default HomeRoute;
