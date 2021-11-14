import React from 'react'
import {Button, Card, CardContent, Typography} from "@mui/material";
import ArrowRightAltIcon from '@mui/icons-material/ArrowRightAlt';
import { useHistory } from "react-router-dom";


const Flight = (props) => {

    let history = useHistory();
    return (
        <div style={{marginBottom: 10, marginTop: 10, display: "flex", justifyContent: "center"}}>
            <Card>
                <CardContent style={{margin: 20, width: 500}}>
                    <div style={{display: "flex", alignItems: 'center',
                        justifyContent: "space-between"}}>
                        <div>
                            <Typography style={{marginBottom: 10}}>
                                {props.flight.date}
                            </Typography>
                            <Typography style={{display: 'flex'}}>
                                <p style={{marginTop:0}}><strong>({props.flight.startHour})  </strong>{props.flight.origin}  </p>
                                <ArrowRightAltIcon />
                                <p style={{marginTop:0}}> <strong>  ({props.flight.endHour})</strong>  {props.flight.destination}</p>
                            </Typography>
                        </div>
                        <div>
                            <Button type="submit" color="success" variant="contained" style={{paddingBottom: 20, paddingTop: 20}}
                            onClick={() => {history.push('finalize')}}>
                                <div>Select <strong>{'    '}{props.flight.price} zł</strong></div>
                            </Button>
                        </div>
                    </div>
                </CardContent>
            </Card>
        </div>

    )
}

export default Flight
