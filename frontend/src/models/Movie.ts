export default interface Movie {
  id: number;
  title: string;
  adult: boolean;
  genre_ids: number[];
  poster_path: string;
  vote_average: number;
}
