import React, {Fragment} from 'react'
import Button from "@material-ui/core/Button";
import IconButton from "@material-ui/core/IconButton";
import Toolbar from "@material-ui/core/Toolbar";
import AppBar from "@material-ui/core/AppBar";
import {Home} from "@material-ui/icons";
import classes from "./Navbar.module.css"
import {Link} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {logout} from "../../store/authActions";

const Navbar = (props) => {

    const dispatch = useDispatch()
    const isLoggedIn = useSelector(state => state.auth.isLoggedIn)

    let links = (
        <Fragment>
            {isLoggedIn ?
            <>
                <Link to='/my-flights' className={classes.navItem}>
                    <Button color="inherit">My Flights</Button>
                </Link>
                <Link to='/login' className={classes.navItem}>
                    <Button onClick={() => dispatch(logout())} color="inherit">Logout</Button>
                </Link>
            </>:
            <>
                <Link to='/login' className={classes.navItem}>
                    <Button color="inherit">Login</Button>
                </Link>
                <Link to='/register' className={classes.navItem}>
                    <Button color="inherit">Register</Button>
                </Link>
            </>
            }

        </Fragment>
    )
    return (
        <AppBar position="static">
            <Toolbar>
                <Link to='/' className={classes.navItem}>
                    <IconButton edge="start" color="inherit" aria-label="menu">
                        <Home/>
                    </IconButton>
                </Link>
                {links}
            </Toolbar>
        </AppBar>
    )
}

export default Navbar;
