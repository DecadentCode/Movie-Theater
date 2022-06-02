import { useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import CartContext from "../context/CartContext";
import "./Header.css";

const Header = () => {
  const { isLoggedIn, setIsLoggedIn } = useContext(CartContext);
  const navigate = useNavigate();

  const logoutHandler = () => {
    setIsLoggedIn(false);
    navigate("/");
  };

  return (
    <div className="Header">
      <nav className="navLinks">
        <Link to="/" className="navLink">
          Home
        </Link>
        <Link to="/about" className="navLink">
          About
        </Link>

        {isLoggedIn ? (
          <>
            <Link to="/profile" className="navLink">
              Profile
            </Link>
            <button onClick={logoutHandler} id="signOut">
              Logout
            </button>
            <Link to="/cart" className="navLink">
              <span className="material-symbols-outlined">local_mall</span>
            </Link>
          </>
        ) : (
          <Link to="/signin" className="navLink">
            Login
          </Link>
        )}
      </nav>
    </div>
  );
};

export default Header;
