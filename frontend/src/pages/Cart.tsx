import { useContext, useState } from "react";
import PayPal from "../components/PayPal";
import CartContext from "../context/CartContext";
import "./Cart.css";

const Cart = () => {
  const { cart } = useContext(CartContext);
  const [checkout, setCheckout] = useState(false);

  return (
    <div className="Cart">
      <h1>Cart</h1>
      <ul>
        {cart.map((item) => (
          <li key={item.id}>
            {item.name} - x{item.qty} - ${item.price * item.qty}
          </li>
        ))}
      </ul>

      {checkout ? (
        <PayPal />
      ) : (
        <button
          onClick={() => {
            setCheckout(true);
          }}
        >
          Checkout
        </button>
      )}
    </div>
  );
};

export default Cart;
