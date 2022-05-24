import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Form from "./components/Form";
import Header from "./components/Header";
import HomeRoute from "./components/HomeRoute";

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Routes>
          <Route path="/form" element={<Form />} />
          <Route path="/" element={<HomeRoute />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
