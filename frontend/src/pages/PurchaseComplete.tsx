import { useContext } from "react";
import CartContext from "../context/CartContext";
import "./PurchaseComplete.css";

const PurchaseComplete = () => {
  const { oldCart } = useContext(CartContext);
  return (
    <div className="PurchaseComplete">
      <h1>Thank you for your purchase!</h1>
      <h2>Your reciept:</h2>
      <p>
        {oldCart.map((item) => (
          <li key={item.id}>
            {item.name} - x{item.qty} - ${item.price * item.qty}
          </li>
        ))}
      </p>
    </div>
  );
};

export default PurchaseComplete;
