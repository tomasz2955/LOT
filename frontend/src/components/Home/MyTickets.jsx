import React, {useEffect, useState} from "react";
import axios from '../../axios-config'
import {Button, Card, CardContent, Typography} from "@mui/material";
import ArrowRightAltIcon from "@mui/icons-material/ArrowRightAlt";
import {formatDate} from "../utils/DateUtil";


const MyTickets = (props) => {
    const [tickets, setTickets] = useState([])

    useEffect(() => {
        if (localStorage.getItem('userId')) {
            axios.get('/tickets/' + localStorage.getItem('userId')).then(response => {
                setTickets(response.data.tickets)
            })
                .catch((error) => {
                    console.log(error)
                });
        } else {
            window.location.href = '/login'
        }
    }, [])

    return (
        <>
            {
                tickets?.length > 0 ? (tickets.map(
                        (ticket) => <div
                            key={ticket.dateOfPurchase}
                            style={{
                                marginBottom: 10,
                                marginTop: 10,
                                display: "flex",
                                justifyContent: "center",
                                borderRadius: 25
                            }}>
                            <Card>
                                <CardContent style={{margin: 20, width: 800, backgroundColor: "#ffcc00"}}>
                                    <Typography style={{marginBottom: 10}}>
                                        <p style={{fontSize: 12}}><strong>Date of
                                            purchase: {formatDate(ticket.dateOfPurchase)}</strong></p>
                                    </Typography>
                                    <br/>
                                    <div style={{
                                        display: "flex", alignItems: 'center',
                                        justifyContent: "space-between"
                                    }}>
                                        <div style={{width: 600, backgroundColor: "white", padding: 10, borderRadius: 25}}>
                                            <Typography style={{marginBottom: 10}}>
                                                FLIGHT NUMBER: <strong>{ticket.flight.flightNumber}</strong>
                                            </Typography>
                                            <Typography style={{display: 'flex'}}>
                                                <p style={{marginTop: 0}}>
                                                    <strong>({formatDate(ticket.flight.date)}) </strong>{ticket.flight.origin}
                                                </p>
                                                <ArrowRightAltIcon style={{marginLeft: 20, marginRight: 20}}/>
                                                <p style={{marginTop: 0}}>
                                                    <strong> ({formatDate(ticket.flight.date)}) </strong> {ticket.flight.destination}
                                                </p>
                                            </Typography>
                                            <Typography style={{marginBottom: 10}}>
                                                <strong>Price: </strong> {ticket.ticketPrice} zł
                                            </Typography>
                                            <Typography style={{marginBottom: 10}}>
                                                <strong>Amount: </strong> 4
                                            </Typography>
                                        </div>
                                        <div>
                                            <Button type="submit" color="warning" variant="contained"
                                                    style={{paddingBottom: 20, paddingTop: 20}}
                                                    onClick={() => {
                                                    }}>
                                                <div><strong>Return Ticket</strong></div>
                                            </Button>
                                        </div>
                                    </div>
                                </CardContent>
                            </Card>
                        </div>
                    )) :
                    <div
                        style={{
                            marginBottom: 10,
                            marginTop: 40,
                            display: "flex",
                            justifyContent: "center",
                            borderRadius: 25
                        }}>
                        <Card>
                            <CardContent style={{width: 800, backgroundColor: "#ffcc00"}}>
                                <p style={{textAlign: "center"}}><strong>YOU HAVE NO TICKETS TO DISPLAY</strong></p>
                            </CardContent>
                        </Card>
                    </div>
            }
        </>
    )
}

export default MyTickets
