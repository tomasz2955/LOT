import * as actionTypes from "./actionTypes";


const initialState = {
    from: null,
    to: null,
    origin: null,
    destination: null,
    isOneWay: true,
    selectedFlight:  {
        startHour: null,
        endHour: null,
        price: null,
        date: null
    },
    seats: []
}

const flightReducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.SEARCH_FLIGHT_CLICKED: {
            return {
                ...state,
                from: action.searchData.from,
                to: action.searchData.to,
                origin: action.searchData.origin,
                destination: action.searchData.destination,
            }
        }
        case actionTypes.FLIGHT_SELECT_CLICKED: {
            return {
                ...state,
                selectedFlight: action.flight
            }
        }
        case actionTypes.SEAT_SELECTED: {
            return {
                ...state,
                seats: [...state.seats, action.seat]
            }
        }
        case actionTypes.SEAT_UNSELECTED: {
            return {
                ...state,
                seats: state.seats.filter(seat => seat !== action.seat)
            }
        }
        default:
            return state
    }
}


export default flightReducer;
