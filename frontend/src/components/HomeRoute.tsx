import { useEffect, useState } from 'react';
import Movie from '../models/Movie';import { getTrendingMovies } from "../services/MovieService";
import './HomeRoute.css'
import MovieObject from './MovieObject';

const HomeRoute = () => {
  const [movies, setMovies] = useState<Movie[]>([]);

  useEffect(() => {
    getTrendingMovies().then((response) => {
      setMovies(response.results);
    });

    fetch('/api/movies')
      .then(res => res.json())
      .then(data => setMovies(data));
  }, []);

  return (
    <div className='HomeRoute'>
        <h1>
            Welcome to Colonial Drive-In Movies!
        </h1>
        <h2>
            We are a small, family-owned, community-based movie theater.
        </h2>
        <h3>
            NOW PLAYING
        </h3>
        <ul>
        {movies.map((item) => (
          <MovieObject key={item.id} movie={item} />
        ))}
      </ul>
    </div>
  )
};

export default HomeRoute;
