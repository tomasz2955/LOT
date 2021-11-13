import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import './App.css';
import Login from "./components/Auth/Login/Login";
import Navbar from "./components/Auth/Navbar";
import Register from "./components/Auth/Register/Register";
import Home from "./components/Home/Home";

function App() {
  return (
      <Router>
        <Navbar/>
        <div>
          <Switch>
            <Route path="/login">
              <Login />
            </Route>
            <Route path="/register">
                <Register />
            </Route>
            <Route path="/">
                  <Home />
            </Route>
          </Switch>
        </div>
      </Router>
  );
}

export default App;
