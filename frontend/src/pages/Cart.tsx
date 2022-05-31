import { useContext, useState } from "react";
import PayPalButton from "../components/PaypalButton";
import CartContext from "../context/CartContext";
import "./Cart.css";

const Cart = () => {
  const { cart, cartTotal } = useContext(CartContext);
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
        <PayPalButton id={cart[0].name} />
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
