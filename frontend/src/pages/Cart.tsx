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
            {item.name} - ${item.price}
          </li>
        ))}
      </ul>
      <div>Total: ${cartTotal.toFixed(2)}</div>

      {checkout ? (
        <PayPalButton cartItems={cart} />
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
