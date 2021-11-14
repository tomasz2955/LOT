import React from "react";
import SeatMap from "./SeatMap";


const Finalization = (props) => {
    //todo box z summary
    //todo uzyc redux
    return (
        <>
            <div style={{display: "flex", flexDirection: 'column', alignItems: 'center'}}>
                <SeatMap takenSeats={
                    ['A4','B2','F1','A6','B6']
                }/>
            </div>
        </>
    )
}

export default Finalization
