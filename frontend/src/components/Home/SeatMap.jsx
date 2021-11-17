import React from 'react'
import './styles.css'
import {useDispatch, useSelector} from "react-redux";
import {seatSelected, seatUnselected} from "../../store/flightActions";
import {Button, Card, CardContent, Typography} from "@mui/material";
import LocalAtmIcon from '@mui/icons-material/LocalAtm';

const SeatMap = (props) => {

    const rowAlphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H']
    const dispatch = useDispatch()

    const selectedSeats = useSelector(state => {
        return state.flight.seats
    })

    const flightState = useSelector(state => {
        return state.flight
    })

    function isSeatTaken(seatNum: string) {
        return props.takenSeats.includes(seatNum)
    }

    function selectSeat(seatNum: string) {
        selectedSeats.includes(seatNum) ? dispatch(seatUnselected(seatNum)) : dispatch(seatSelected(seatNum))
    }

    function calculateTotal() {
        return +flightState.selectedFlight.price * selectedSeats.length;
    }

    return (
        <>
            <div style={{display: 'flex'}}>
                <div>
                    <div style={{textAlign: 'center'}}>
                        <h1>Select your seats: </h1>
                    </div>
                    {
                        rowAlphabet.map(
                            letter => {
                                return (
                                    <div style={{display: "flex"}} key={letter}>
                                        <h2 style={{
                                            marginTop: 25,
                                            marginRight: 10,
                                            marginLeft: 10,
                                            marginBottom: 0
                                        }}>{letter}</h2>
                                        <button disabled={isSeatTaken({letter}.letter + '1')}
                                                className={isSeatTaken({letter}.letter + 1) ? 'takenSeat' : 'seat'}
                                                value={{letter}.letter + 1}
                                                onClick={(event) => selectSeat(event.target.value)}
                                                style={{backgroundColor: selectedSeats.includes({letter}.letter + 1) && 'green'}}
                                        >1
                                        </button>
                                        <button disabled={isSeatTaken({letter}.letter + 2)}
                                                className={isSeatTaken({letter}.letter + 2) ? 'takenSeat' : 'seat'}
                                                value={{letter}.letter + 2}
                                                onClick={(event) => selectSeat(event.target.value)}
                                                style={{backgroundColor: selectedSeats.includes({letter}.letter + 2) && 'green'}}
                                        >2
                                        </button>
                                        <button disabled={isSeatTaken({letter}.letter + 3)}
                                                className={isSeatTaken({letter}.letter + 3) ? 'takenSeat' : 'seat'}
                                                value={{letter}.letter + 3}
                                                onClick={(event) => selectSeat(event.target.value)}
                                                style={{backgroundColor: selectedSeats.includes({letter}.letter + 3) && 'green'}}>3
                                        </button>
                                        <p style={{margin: 20}}/>
                                        <button disabled={isSeatTaken({letter}.letter + 4)}
                                                className={isSeatTaken({letter}.letter + 4) ? 'takenSeat' : 'seat'}
                                                value={{letter}.letter + 4}
                                                onClick={(event) => selectSeat(event.target.value)}
                                                style={{backgroundColor: selectedSeats.includes({letter}.letter + 4) && 'green'}}
                                        >4
                                        </button>
                                        <button disabled={isSeatTaken({letter}.letter + 5)}
                                                className={isSeatTaken({letter}.letter + 5) ? 'takenSeat' : 'seat'}
                                                value={{letter}.letter + 5}
                                                onClick={(event) => selectSeat(event.target.value)}
                                                style={{backgroundColor: selectedSeats.includes({letter}.letter + 5) && 'green'}}
                                        >5
                                        </button>
                                        <button disabled={isSeatTaken({letter}.letter + 6)}
                                                className={isSeatTaken({letter}.letter + 6) ? 'takenSeat' : 'seat'}
                                                value={{letter}.letter + 6}
                                                onClick={(event) => selectSeat(event.target.value)}
                                                style={{backgroundColor: selectedSeats.includes({letter}.letter + 6) && 'green'}}
                                        >6
                                        </button>
                                    </div>
                                )
                            }
                        )
                    }
                </div>
                <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
                    <Card style={{minWidth: 400, marginLeft: 100 , borderStyle: 'solid', borderColor: 'cornflowerblue'}}>
                        <CardContent>
                            <Typography sx={{ fontSize: 20 }} color="text.secondary" gutterBottom>
                               <strong>Date: </strong> {flightState.selectedFlight.date}
                            </Typography>
                            <Typography sx={{ fontSize: 20 }} color="text.secondary" gutterBottom>
                                <strong>Origin: </strong> {flightState.origin} ({flightState.selectedFlight.startHour})
                            </Typography>
                            <Typography sx={{ fontSize: 20 }} color="text.secondary" gutterBottom>
                                <strong>Destination: </strong> {flightState.destination} ({flightState.selectedFlight.endHour})
                            </Typography>
                            <Typography sx={{ fontSize: 20 }} color="text.secondary" gutterBottom>
                                <strong>Total: </strong>  {calculateTotal()} [zł]
                            </Typography>
                            <div style={{textAlign: 'center'}}>
                                <Button type="submit" variant="contained"
                                        endIcon={<LocalAtmIcon />}
                                        color="success">Purchase</Button>
                            </div>
                        </CardContent>
                    </Card>
                </div>
            </div>
        </>
    )
}

export default SeatMap
