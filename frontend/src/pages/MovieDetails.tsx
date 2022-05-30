import { useEffect, useState } from "react";
import { useParams } from "react-router";
import MovieObject from "../components/MovieObject";
import Tickets from "../components/Tickets";
import SingleMovie from "../models/SingleMovie";
import { getMovieById } from "../services/MovieService";
import "./MovieDetails.css";

const MovieDetails = () => {
  const [movie, setMovie] = useState<SingleMovie>();
  const movieRuntime: number | undefined = movie?.runtime;
  const id: string = useParams().id!;

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

  useEffect(() => {
    getMovieById(id).then((response) => {
      setMovie(response);
      console.log(response);
    });
  }, [id]);

  return (
    <div className="MovieDetails">
      <MovieObject movie={movie!} />
      <Tickets movie={movie} />

      <div className="MovieDescriptionContainer">
        <p id="description">
          Runtime: {setRunTime(movieRuntime)}
          <br />
          Release Date: {movie?.release_date}
          <br />
          <br /> Description: {movie?.overview}
        </p>
      </div>
    </div>
  );
};

export default MovieDetails;
