import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import CartContext from "../context/CartContext";
import Movie from "../models/Movie";
import "./MovieObject.css";
import Tickets from "./Tickets";

interface Props {
  movie: Movie;
}

const MovieObject = ({ movie }: Props) => {
  const { isLoggedIn } = useContext(CartContext);
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
      {isLoggedIn && <Tickets movie={movie} />}
    </div>
  );
};

export default MovieObject;
