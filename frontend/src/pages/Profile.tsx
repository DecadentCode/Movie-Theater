import { useContext } from "react";
import CartContext from "../context/CartContext";
import "./Profile.css";

const Profile = () => {
  const { purchaseHistory } = useContext(CartContext);
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
