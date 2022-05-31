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
        {/* <Link to="/store" className="navLink">
          Store
        </Link> */}
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
          </>
        ) : (
          <a href="/oauth2/authorization/github">
            <img
              id="LoginImage"
              src="https://cloud.githubusercontent.com/assets/194400/11214293/4e309bf2-8d38-11e5-8d46-b347b2bd242e.png"
            ></img>
          </a>
        )}
        <Link to="/cart" className="navLink">
          <span className="material-symbols-outlined">local_mall</span>
        </Link>
      </nav>
    </div>
  );
};

export default Header;
