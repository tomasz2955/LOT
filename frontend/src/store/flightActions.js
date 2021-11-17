import * as actionTypes from "./actionTypes";
import {SEAT_SELECTED} from "./actionTypes";


export const searchFlightClicked = (searchData) => {
    return {
        type: actionTypes.SEARCH_FLIGHT_CLICKED,
        searchData: searchData
    }
}


export const flightSelectClicked = (flight) => {
    return {
        type: actionTypes.FLIGHT_SELECT_CLICKED,
        flight: flight
    }
}

export const seatSelected = (seat) => {
    return {
        type: actionTypes.SEAT_SELECTED,
        seat: seat
    }
}

export const seatUnselected = (seat) => {
    return {
        type: actionTypes.SEAT_UNSELECTED,
        seat: seat
    }
}

