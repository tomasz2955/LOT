import React from "react";
import SeatMap from "./SeatMap";


const Finalization = (props) => {
    return (
        <>
            <div style={{display: 'flex', justifyContent: 'center'}}>
                <SeatMap takenSeats={
                    ['A4','B2','F1','A6','B6']
                }/>
            </div>

        </>
    )
}

export default Finalization
