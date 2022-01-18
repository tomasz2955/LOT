import * as actionTypes from "./actionTypes";
import axios from '../axios-config'

export const loginStart = () => {
    return {
        type: actionTypes.LOGIN_START
    }
}

export const checkLocalStorageIfLoggedIn = () => {
    const isLoggedIn = localStorage.getItem('userId')

    return {
        type: actionTypes.CHECK_LOCAL_STORAGE_IF_LOGGED_IN,
        isLoggedIn: isLoggedIn
    }
}

export const login = (email, password) => {
    return dispatch => {
        dispatch(loginStart())
        axios.post('/login', {email, password}).then(response => {
            localStorage.setItem('userId', response.data.id)
            localStorage.setItem('expirationDate', response.data.localDate)
            dispatch(loginSuccess(response.data.id))
        })
            .catch(() => {
                dispatch(loginFailed("Wrong username or password"))
            });
    }
}

export const loginSuccess = (token) => {
    return {
        type: actionTypes.LOGIN_SUCCESS,
        token: token
    }
}

export const loginFailed = (error) => {
    return {
        type: actionTypes.LOGIN_FAILURE,
        error: error
    }
}

export const logout = () => {
    return {
        type: actionTypes.LOGOUT
    }
}
