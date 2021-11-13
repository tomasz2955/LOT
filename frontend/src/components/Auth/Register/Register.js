import React, {Fragment, useEffect, useState} from 'react'
import classes from './Register.module.css'
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import CircularProgress from "@material-ui/core/CircularProgress";
import {Link} from "react-router-dom";
import axios from '../../../axios-config'

const Register = (props) => {

  const [name, setName] = useState('Imie32123')
  const [lastName, setLastName] = useState('$^N&%A*%^&$#&Z%$^%WISKO')
  const [email, setEmail] = useState('niepoprawny email ale kto mi zabroni')
  const [phoneNumber, setPhoneNumber] = useState('dwa dwa osiem trzy... dalej nie pamietam')
  const [password, setPassword] = useState('guest')
  const [backendResponse, setBackendResponse] = useState('')

  const loading = false

  useEffect(() => {
  }, [])



  const handleChange = (event) => {
    if (event.target.id === 'email') {
      setEmail(event.target.value)
    } else if(event.target.id === 'name') {
      setName(event.target.value)
    } else if(event.target.id === 'lastName') {
      setLastName(event.target.value)
    } else if(event.target.id === 'phoneNumber') {
      setPhoneNumber(event.target.value)
    } else if(event.target.id === 'password') {
      setPassword(event.target.value)
    }
  }

  const handleSubmit = (event) => {
    setBackendResponse('')
    event.preventDefault()
    axios.post('/register', {
      id: 'taki_id_sobie_wymyslilem',
      name: name,
      lastName: lastName,
      email: email,
      phoneNumber: phoneNumber,
    }).then(response => {
      setBackendResponse('Udalo sie zarejestrowac uzytkownika')
    }).catch(() => {
      console.log('error')
      setBackendResponse('error')
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
                        className={classes.pageTitle}>Register</Typography>
            <form noValidate onSubmit={handleSubmit}>
              <TextField id="name" name="name" type="text" label="name"
                         className={classes.textField}
                         value={name} onChange={handleChange} fullWidth/>
              <TextField id="lastName" name="lastName" type="text" label="lastName"
                         className={classes.textField}
                         value={lastName} onChange={handleChange} fullWidth/>
              <TextField id="phoneNumber" name="phoneNumber" type="text" label="phoneNumber"
                         className={classes.textField}
                         value={phoneNumber} onChange={handleChange} fullWidth/>
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
              <small><Link to="/login">Sign in</Link></small>
            </form>
          </Grid>
          <Grid item sm/>
        </Grid>
        <h3>{backendResponse}</h3>
      </Fragment>
  )
}

export default Register;
