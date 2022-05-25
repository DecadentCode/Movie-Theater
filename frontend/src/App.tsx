import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Form from "./components/Form";
import Header from "./components/Header";
import HomeRoute from "./components/HomeRoute";
import MovieDetails from "./components/MovieDetails";
import SignIn from "./components/SignIn";
import SignUp from "./components/Signup";

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Routes>
          <Route path="/form" element={<Form />} />
          <Route path="/" element={<HomeRoute />} />
          <Route path="/movie/:id" element={<MovieDetails />} />
          <Route path="/signin" element={<SignIn />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="*" element={<div>Not found</div>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
