import { useContext, useState } from "react";
import CartContext from "../context/CartContext";
import Movie from "../models/Movie";
import "./Tickets.css";

interface Props {
  movie: Movie | undefined;
}

const Tickets = ({ movie }: Props) => {
  const { addToCart } = useContext(CartContext);
  const [ticketPopup, setTicketPopup] = useState(false);
  const [showtime, setShowtime] = useState("");

  const ticketsHandler = () => {
    setTicketPopup(!ticketPopup);
    setShowtime("");
  };
  const showtimeHandler = (e: any) => {
    setShowtime(e.target.value);
    console.log(e.target.value);
  };

  const submitHandler = (e: any) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);
    const adultqty: number = parseInt(formData.get("adultqty") as string);

    if (!adultqty) {
      alert("Please select at least one ticket");
      return;
    }
    adultqty &&
      addToCart(
        {
          id: `adultTicket-${movie!.id}-${showtime}`,
          name: `Adult Ticket (${movie!.title}) - ${showtime}`,
          price: 10,
          qty: 1,
        },
        adultqty
      );

    ticketsHandler();
  };

  return (
    <div className="Tickets">
      {ticketPopup && (
        <div className="TicketsPopupOutside">
          <div
            className="TicketsPopupContainer"
            style={{
              backgroundImage: `url(https://www.themoviedb.org/t/p/w500${movie?.poster_path})`,
            }}
          >
            <div className="TicketsPopupContent">
              <button onClick={ticketsHandler} className="TicketsPopupClose">
                X
              </button>
              <h1>Showtimes for {movie?.title}</h1>
              <div className="TicketsShowtimesContainer">
                <section className="TicketsShowtimes">
                  <button
                    className="ShowtimesButton"
                    onClick={showtimeHandler}
                    value="10:00PM"
                  >
                    10:00 PM
                  </button>
                  <button
                    className="ShowtimesButton"
                    onClick={showtimeHandler}
                    value="12:00AM"
                  >
                    12:00 AM
                  </button>
                  <button
                    className="ShowtimesButton"
                    onClick={showtimeHandler}
                    value="2:00AM"
                  >
                    2:00 AM
                  </button>
                </section>

                {(showtime && (
                  <form className="TicketsForm" onSubmit={submitHandler}>
                    <h2>Purchase Tickets for {showtime}</h2>
                    <label>
                      Adult - $10/each
                      <input
                        type="number"
                        name="adultqty"
                        className="TicketsQty"
                        id="AdultQty"
                        defaultValue={1}
                        min={0}
                        autoComplete="off"
                      />
                      <button type="submit" className="TicketsButton">
                        Add to Cart
                      </button>
                    </label>
                  </form>
                )) || <h2>Please select a showtime</h2>}
              </div>
            </div>
          </div>
        </div>
      )}

      <button className="TicketsButton" onClick={ticketsHandler}>
        Buy Tickets
      </button>
    </div>
  );
};

export default Tickets;
