import { useEffect, useState } from "react";
import { useParams } from "react-router";
import SingleMovie from "../models/SingleMovie";
import { getMovieById } from "../services/MovieService";
import "./MovieDetails.css";
import MovieObject from "./MovieObject";

interface RouteParams {
  id: string;
}

const MovieDetails = () => {
  const [movie, setMovie] = useState<SingleMovie>();
  let id = useParams().id!;

  useEffect(() => {
    getMovieById(id).then((response) => {
      setMovie(response);
    });
  }, [id]);

  const movieRuntime: number | undefined = movie?.runtime;
  const setRunTime = (time: number | undefined) => {
    if (time) {
      const hours = Math.trunc(time / 60);
      const minutes = time % 60;
      if (hours === 1) {
        return `${hours} hour, ${minutes} minutes`;
      } else {
        return `${hours} hours, ${minutes} minutes`;
      }
    }
  };

  return (
    <div className="MovieDetails">
      <MovieObject movie={movie!} />

      <p id="description">
        Runtime: {setRunTime(movieRuntime)}
        <br />
        Release Date: {movie?.release_date}
        <br /> Description: {movie?.overview}
      </p>
    </div>
  );
};

export default MovieDetails;
