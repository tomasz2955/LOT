import React, {Fragment, useEffect, useState} from 'react'
import classes from './Login.module.css'
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import CircularProgress from "@material-ui/core/CircularProgress";
import {Link} from "react-router-dom";
import axios from '../../../axios-config'

const Login = (props) => {

  const [email, setEmail] = useState('guest')
  const [password, setPassword] = useState('guest')

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
    console.log(email)
    console.log(password)
    axios.post('/login', {
      email: 'przeciez Takiego Emailu Nie Mozna miec ale nikt mi nie zabrania',
      password: 'haslo',
    }).then(response => {
      console.log('udalo sie zalogowac')
    }).catch((error) => {
      console.log(error)
      console.log('cos wybuchlo podczas logowania')
    });
  }

  return (
      <Fragment>
        <Grid container className={classes.form}>
          <Grid item sm/>
          <Grid item sm>
            <img className={classes.image}
                 src="https://media.istockphoto.com/vectors/right-arrow-vector-icon-on-transparent-background-right-arrow-icon-vector-id1013490744"
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
      </Fragment>
  )
}

export default Login;
