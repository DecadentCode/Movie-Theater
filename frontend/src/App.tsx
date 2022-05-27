import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Form from "./components/Form";
import Header from "./components/Header";
import Cart from "./pages/Cart";
import HomeRoute from "./pages/HomeRoute";
import MovieDetails from "./pages/MovieDetails";
import Profile from "./pages/Profile";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/Signup";
import Store from "./pages/Store";

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Routes>
          <Route path="/form" element={<Form />} />
          <Route path="/" element={<HomeRoute />} />
          <Route path="/movie/:id" element={<MovieDetails />} />
          <Route path="/store" element={<Store />} />
          <Route path="/signin" element={<SignIn />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="*" element={<div>Not found</div>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
