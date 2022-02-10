package com.example.LOT.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String airline;
    private LocalDateTime departureDate;
    private LocalDateTime dateOfArrival;
    private Double price;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Seat> seats;

    public Flight(String flightNumber, String origin, String destination, String airline, LocalDateTime departureDate, LocalDateTime dateOfArrival, Double price, List<Seat> seats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.departureDate = departureDate;
        this.dateOfArrival = dateOfArrival;
        this.price = price;
        this.seats = seats;
    }

    public Flight() {
    }

    public boolean isSeatTaken(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                if (seat.getPassengerId() == null) {
                    return false;
                }
            }
        }
        return true;
    }


    public void setSeatBusy(String seatNumber, Long passengerId) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                if (seat.getPassengerId() == null) {
                    seat.setPassengerId(passengerId);
                }
            }
        }
    }

    public void setSeatFree(Long passengerId) {
        for (Seat seat : seats) {
            if (Objects.equals(seat.getPassengerId(), passengerId)) {
                seat.setPassengerId(null);

            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDateTime dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}


