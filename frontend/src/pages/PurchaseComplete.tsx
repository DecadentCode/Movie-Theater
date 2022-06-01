import { useContext } from "react";
import CartContext from "../context/CartContext";
import "./PurchaseComplete.css";

const PurchaseComplete = () => {
  const { cart, cartTotal } = useContext(CartContext);
  return (
    <div className="PurchaseComplete">
      <h1>Thank you for your purchase!</h1>
      <h2>Your reciept:</h2>
      <p>
        {cart.map((item) => (
          <li key={item.id}>
            {item.name} - x{item.qty} - ${item.price * item.qty}
          </li>
        ))}
        {/* Total Cost: ${cartTotal} */}
      </p>
    </div>
  );
};

export default PurchaseComplete;
