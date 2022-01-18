import * as actionTypes from "./actionTypes";

const initialState = {
    loading: false,
    error: null,
    isLoggedIn: false,
    user: null
}

const authReducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.LOGIN_START: {
            return {
                ...state,
                loading: true
            }
        }
        case actionTypes.LOGIN_SUCCESS: {
            return {
                ...state,
                loading: false,
                isLoggedIn: true
            }
        }
        case actionTypes.LOGIN_FAILURE: {
            return {
                ...state,
                loading: false,
                error: action.error
            }
        }
        case actionTypes.CHECK_LOCAL_STORAGE_IF_LOGGED_IN: {
            return {
                ...state,
                isLoggedIn: action.isLoggedIn
            }
        }
        case actionTypes.LOGOUT: {
            localStorage.clear()
            return {
                ...state,
                isLoggedIn: false,
                token: null
            }
        }
        default:
            return state
    }
}


export default authReducer;
