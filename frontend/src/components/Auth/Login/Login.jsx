import React, {Fragment, useEffect, useState} from 'react'
import classes from './Login.module.css'
import {Link, Redirect} from "react-router-dom";
import {CircularProgress, Grid, TextField, Typography} from "@mui/material";
import Button from "@mui/material/Button";
import {login} from "../../../store/authActions";
import {useDispatch, useSelector} from "react-redux";

const Login = (props) => {

  const [email, setEmail] = useState('bator@wp.pl')
  const [password, setPassword] = useState('qwerty')
  const isLoggedIn = useSelector(state => state.auth.isLoggedIn)
  const dispatch = useDispatch()

  const loading = false

  useEffect(() => {
  }, [])



  const handleChange = (event) => {
    if (event.target.id === 'email') {
      setEmail(event.target.value)
    } else {
      setPassword(event.target.value)
    }
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    dispatch(login(email, password))
  }

  return (
      <Fragment>
        <Grid container className={classes.form}>
          <Grid item sm/>
          <Grid item sm>
            <img className={classes.image}
                 src="https://www.creativefabrica.com/wp-content/uploads/2020/02/13/Letter-Logo-Graphics-1-59-580x386.jpg"
                 alt="monkey"/>
            <Typography variant="h3"
                        className={classes.pageTitle}>Login</Typography>
            <form noValidate onSubmit={handleSubmit}>
              <TextField id="email" name="email" type="text" label="email"
                         className={classes.textField}
                         value={email} onChange={handleChange} fullWidth/>
              <TextField id="password" name="password" type="password"
                         label="password" className={classes.textField}
                         value={password} onChange={handleChange} fullWidth/>
              <div className={classes.button}>
                <Button type="submit" variant="contained" disabled={loading}
                        color="primary">{loading ? <CircularProgress/>
                    : 'Submit'}</Button>
              </div>
              <small><Link to="/register">Sign up</Link></small>
            </form>
          </Grid>
          <Grid item sm/>
        </Grid>
        {isLoggedIn && <Redirect
            to={{
              pathname: "/"
            }}
        />}
      </Fragment>
  )
}

export default Login;
