export default interface SingleMovie {
  id: number;
  title: string;
  adult: boolean;
  genre_ids: number[];
  poster_path: string;
  runtime: number;
  overview: string;
  certification: string;
  vote_average: number;
  release_date: string;
}
