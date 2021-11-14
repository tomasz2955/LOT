import React, {Fragment, useState} from 'react'
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import DateRangePicker from '@mui/lab/DateRangePicker';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import Box from '@mui/material/Box';
import plane from '../../static/plane.png'
import {Autocomplete, FormControlLabel, Radio, RadioGroup} from "@mui/material";
import {DatePicker} from "@mui/lab";
import Flights from "./Flights";

export const mockFlights = [
    {
        id: "123",
        origin: "Warszawa",
        destination: "Wroclaw",
        date: "19/07/2021",
        startHour: "06:10",
        endHour: "11:30",
        price: "1499"
    },
    {
        id: "12433",
        origin: "Wroclaw",
        destination: "Krakow",
        date: "29/07/2021",
        startHour: "16:10",
        endHour: "21:30",
        price: "11"
    },
    {
        id: "12433",
        origin: "Bytom",
        destination: "Wroclaw",
        date: "19/12/2022",
        startHour: "08:10",
        endHour: "12:30",
        price: "4199"
    },
    {
        id: "12563",
        origin: "Szczecin",
        destination: "Wroclaw",
        date: "02/02/2022",
        startHour: "22:10",
        endHour: "23:30",
        price: "4199"
    },
    {
        id: "1263",
        origin: "Szczecin",
        destination: "Bytom",
        date: "11/11/2022",
        startHour: "07:20",
        endHour: "10:30",
        price: "1992"
    },
]



const Home = (props) => {
    //todo wybieranie kraju z dropdown
    //todo liczba pasazerow
    //todo w dwie strony

    const cities = ['Wroclaw', 'Warszawa', 'Szczecin', 'Bytom', 'Karpacz']

    const [filteredCities, setFilteredCities] = useState([])
    const [isOneWay, setIsOneWay] = useState(true)
    const [origin, setOrigin] = useState('')
    const [destination, setDestination] = useState('')
    const [dateRange, setDateRange] = useState([null, null])


    const [foundFLights, setFoundFlights] = useState([])


    return (
            <Fragment>
                <div style={{display: "flex", justifyContent: "center", marginTop: 50}}>
                    <div style={{display: "flex", flexDirection: "column", justifyContent: "center", width: 500}}>
                        <img src={plane} alt="plane"/>
                        <RadioGroup row aria-label="gender" name="row-radio-buttons-group" defaultValue="true">
                            <FormControlLabel value="true" control={<Radio />} label="One Way"
                            onClick={() => setIsOneWay(true)}/>
                            <FormControlLabel value="false" control={<Radio />} label="Both Ways"
                                              onClick={() => setIsOneWay(false)}/>
                        </RadioGroup>
                        <Autocomplete
                            style={{marginTop: 10, marginBottom: 5}}
                            inputValue={origin}
                            onInputChange={(event, input) => {
                                if (input) {
                                    setFilteredCities(cities.filter(city => city.includes(input)))
                                }
                                setOrigin(input)
                            }}
                            options={filteredCities}
                            renderInput={(params) => <TextField {...params} label="Origin" />}
                        />
                        <Autocomplete
                            style={{marginTop: 5, marginBottom: 10}}
                            inputValue={destination}
                            onInputChange={(event, input) => {
                                if (input) {
                                    setFilteredCities(cities.filter(city => city.includes(input)))
                                } else {
                                    setFilteredCities([])
                                }
                                setDestination(input)
                            }}
                            options={filteredCities}
                            renderInput={(params) => <TextField {...params} label="Destination" />}
                        />

                        { !isOneWay ?
                            <div style={{display: "flex", justifyContent: "center", marginBottom: 10}}>
                                <LocalizationProvider dateAdapter={AdapterDateFns}>
                                    <DateRangePicker
                                        startText="From"
                                        endText="To"
                                        value={dateRange}
                                        onChange={(dateRange) => {
                                            setDateRange(dateRange);
                                        }}
                                        renderInput={(startProps, endProps) => (
                                            <Fragment>
                                                <TextField {...startProps} />
                                                <Box sx={{ mx: 2 }}> to </Box>
                                                <TextField {...endProps} />
                                            </Fragment>
                                        )}
                                    />
                                </LocalizationProvider>
                            </div> : <div  style={{display: "flex", justifyContent: "center", marginBottom: 10}}>
                                <LocalizationProvider dateAdapter={AdapterDateFns}>
                                    <DatePicker
                                        label="From"
                                        value={dateRange[0]}
                                        onChange={(newValue) => {
                                            setDateRange([newValue, null]);
                                        }}
                                        renderInput={(params) => <TextField {...params} />}
                                    />
                                </LocalizationProvider>
                            </div>

                        }

                        <Button variant="contained"
                        onClick={() => {
                            console.log(origin)
                            console.log(destination)
                            console.log(new Date(dateRange[0]).toISOString())
                            console.log(dateRange[1] ? new Date(dateRange[1]).toISOString(): null)
                            setFoundFlights(mockFlights.filter(flight => flight.origin === origin || flight.destination === destination))
                        }}>Search</Button>
                    </div>
                </div>
                <br/>
                <br/>
                <br/>
                <Flights flights={foundFLights}/>
            </Fragment>
        )

}

export default Home
