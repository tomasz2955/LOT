import React, {Fragment, useState} from 'react'
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import DateRangePicker from '@mui/lab/DateRangePicker';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import Box from '@mui/material/Box';
import plane from '../../static/plane.png'
import {Autocomplete} from "@mui/material";

const Home = (props) => {
    //todo wybieranie kraju z dropdown

    const cities = ['Wroclaw', 'Warszawa', 'Szczecin', 'Bytom', 'Karpacz']

    const [filteredCities, setFilteredCities] = useState([])
    const [origin, setOrigin] = useState('')
    const [destination, setDestination] = useState('')
    const [dateRange, setDateRange] = useState([null, null])


    return (
            <Fragment>
                <div style={{display: "flex", justifyContent: "center", marginTop: 50}}>
                    <div style={{display: "flex", flexDirection: "column", justifyContent: "center", width: 500}}>
                        <img src={plane} alt="plane"/>
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
                        </div>
                        <Button variant="contained"
                        onClick={() => {
                            console.log(origin)
                            console.log(destination)
                            console.log(dateRange)
                        }}>Search</Button>
                    </div>
                </div>


            </Fragment>
        )

}

export default Home
