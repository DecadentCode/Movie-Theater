import { useState } from "react";
import { Link } from "react-router-dom";
import "./Header.css";

const Header = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(true);

  const logoutHandler = () => {
    setIsLoggedIn(false);
  };

  return (
    <div className="Header">
      <nav className="navLinks">
        <Link to="/" className="navLink">
          Home
        </Link>
        {isLoggedIn ? (
          <>
            <Link to="/profile" className="navLink">
              Profile
            </Link>
            <button onClick={logoutHandler} id="signOut">
              Logout
            </button>
          </>
        ) : (
          <Link to="/signin" className="navLink">
            Sign In
          </Link>
        )}
      </nav>
    </div>
  );
};

export default Header;
