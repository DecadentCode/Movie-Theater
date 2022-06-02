import { useContext } from "react";
import CartContext from "../context/CartContext";
import "./PurchaseComplete.css";

const PurchaseComplete = () => {
  const { oldCart } = useContext(CartContext);
  return (
    <div className="PurchaseComplete">
      <h1>Thank you for your purchase!</h1>
      <h2>Your reciept:</h2>
      <ul>
        {oldCart.map((item) => (
          <li key={item.id}>
            {item.name} - ${item.price}
          </li>
        ))}
      </ul>
      <p>Total: ${oldCart.length * 10}</p>
    </div>
  );
};

export default PurchaseComplete;
