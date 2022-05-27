import { useNavigate } from "react-router-dom";
import Movie from "../models/Movie";
import "./MovieObject.css";

interface Props {
  movie: Movie;
}

const MovieObject = ({ movie }: Props) => {
  const navigate = useNavigate();

  const seeDetails = (id: number): void => {
    navigate(`/movie/${encodeURIComponent(id)}`);
  };

  return (
    <div className="MovieObject">
      <p className="title">{movie?.title}</p>
      <img
        src={`https://www.themoviedb.org/t/p/w500${movie?.poster_path}`}
        alt="movie-poster"
        onClick={() => seeDetails(movie?.id)}
      />
    </div>
  );
};

export default MovieObject;
