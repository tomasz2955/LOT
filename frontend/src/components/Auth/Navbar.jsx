import React, {Fragment} from 'react'
import Button from "@material-ui/core/Button";
import IconButton from "@material-ui/core/IconButton";
import Toolbar from "@material-ui/core/Toolbar";
import AppBar from "@material-ui/core/AppBar";
import {Home} from "@material-ui/icons";
import classes from "./Navbar.module.css"
import {Link} from "react-router-dom";

const Navbar = (props) => {

    let links = (
        <Fragment>
            <Link to='/login' className={classes.navItem}>
                <Button color="inherit">Login</Button>
            </Link>
            <Link to='/register' className={classes.navItem}>
                <Button color="inherit">Register</Button>
            </Link>
        </Fragment>
    )
    return (
        <AppBar position="static">
            <Toolbar>
                <IconButton edge="start" color="inherit" aria-label="menu">
                    <Home />
                </IconButton>
                {links}
            </Toolbar>
        </AppBar>
    )
}

export default Navbar;
