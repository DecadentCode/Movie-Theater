import { useContext, useEffect } from "react";
import CartContext from "../context/CartContext";
import { getAllPurchases } from "../services/AccountService";
import "./Profile.css";

const Profile = () => {
  const { purchaseHistory, setPurchaseHistory } = useContext(CartContext);

  useEffect(() => {
    getAllPurchases().then((purchases) => {
      setPurchaseHistory(purchases);
      console.log(purchases);
    });
  }, [setPurchaseHistory]);

  return (
    <div className="Profile">
      <h1>Profile</h1>

      <ul>
        {purchaseHistory.map((item) => (
          <li key={item}>{item}</li>
        ))}
      </ul>
    </div>
  );
};

export default Profile;
