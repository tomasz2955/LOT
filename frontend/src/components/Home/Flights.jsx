import React from 'react'
import Flight from "./Flight";

const Flights = (props) => {

    return (
      <>
          {
              props.flights.map(
                  (flight) => <Flight key={flight.id} flight={flight}/>
              )
          }
          </>
    )
}

export default Flights
