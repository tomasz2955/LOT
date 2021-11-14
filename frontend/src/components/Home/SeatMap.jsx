import React, {useState} from 'react'
import './styles.css'

const SeatMap = (props) => {

    const rowAlphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H']
    const [selectedSeats, setSelectedSeats] = useState([])
    function isSeatTaken(seatNum: string) {
        return props.takenSeats.includes(seatNum)
    }

    function selectSeat(seatNum: string) {
        !selectedSeats.includes(seatNum) ? setSelectedSeats([...selectedSeats, seatNum]): setSelectedSeats(selectedSeats.filter(seat => seat !== seatNum))
    }

    return (
        <>
            <h1>Select your seats:</h1>
            {
              rowAlphabet.map(
                  letter => {
                      return (
                      <div style={{display: "flex"}} key={letter} >
                          <h2 style={{marginTop:25, marginRight:10, marginLeft: 10, marginBottom:0}}>{letter}</h2>
                          <button disabled={isSeatTaken({letter}.letter + '1')} className={isSeatTaken({letter}.letter + 1) ? 'takenSeat': 'seat'}
                                  value={{letter}.letter + 1}
                          onClick={(event) => selectSeat(event.target.value)} style={{backgroundColor: selectedSeats.includes({letter}.letter + 1) && 'green'}}
                          >1</button>
                          <button disabled={isSeatTaken({letter}.letter + 2)} className={isSeatTaken({letter}.letter + 2) ? 'takenSeat': 'seat'}
                                  value={{letter}.letter + 2}
                                  onClick={(event) => selectSeat(event.target.value)} style={{backgroundColor: selectedSeats.includes({letter}.letter + 2) && 'green'}}
                          >2</button>
                          <button disabled={isSeatTaken({letter}.letter + 3)} className={isSeatTaken({letter}.letter + 3) ? 'takenSeat': 'seat'}
                                  value={{letter}.letter + 3}
                                  onClick={(event) => selectSeat(event.target.value)} style={{backgroundColor: selectedSeats.includes({letter}.letter + 3) && 'green'}}>3</button>
                          <p style={{margin: 20}}/>
                          <button disabled={isSeatTaken({letter}.letter + 4)} className={isSeatTaken({letter}.letter + 4) ? 'takenSeat': 'seat'}
                                  value={{letter}.letter + 4}
                                  onClick={(event) => selectSeat(event.target.value)} style={{backgroundColor: selectedSeats.includes({letter}.letter + 4) && 'green'}}
                          >4</button>
                          <button disabled={isSeatTaken({letter}.letter + 5)} className={isSeatTaken({letter}.letter + 5) ? 'takenSeat': 'seat'}
                                  value={{letter}.letter + 5}
                                  onClick={(event) => selectSeat(event.target.value)} style={{backgroundColor: selectedSeats.includes({letter}.letter + 5) && 'green'}}
                          >5</button>
                          <button disabled={isSeatTaken({letter}.letter + 6)} className={isSeatTaken({letter}.letter + 6) ? 'takenSeat': 'seat'}
                                  value={{letter}.letter + 6}
                                  onClick={(event) => selectSeat(event.target.value)} style={{backgroundColor: selectedSeats.includes({letter}.letter + 6) && 'green'}}
                          >6</button>
                      </div>
                      )
                  }
              )
            }
        </>
    )
}

export default SeatMap
