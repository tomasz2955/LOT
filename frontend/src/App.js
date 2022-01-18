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
import Finalization from "./components/Home/Finalization";
import MyTickets from "./components/Home/MyTickets";
import {useEffect} from "react";
import {useDispatch} from "react-redux";
import {checkLocalStorageIfLoggedIn} from "./store/authActions";

function App() {

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(checkLocalStorageIfLoggedIn())
    }, [])


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
            <Route path="/my-flights">
                <MyTickets/>
            </Route>
            <Route path="/finalize">
                  <Finalization />
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
