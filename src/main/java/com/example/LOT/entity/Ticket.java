package com.example.LOT.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @ManyToOne(cascade = {CascadeType.ALL}) //nie wiem czy ten cascade all tutaj jest dobry. To oznaczałoby ze jak usuniesz ticketa to chcesz zeby kaskadowo tez usunał się lot a tego nie chcemy
    //wydaje mi sie ze to powinno byc od strony lotu - gdy usuwasz lot chcesz kaskadowo usunąć wszystie bilety na niego
    @JoinColumn(name = "flight_id")
    private Flight flight;
    private LocalDateTime dateOfPurchase;
    private String seatNumber;

    public Ticket(Long userId, Passenger passenger, Flight flight, LocalDateTime dateOfPurchase, String seatNumber) {
        this.userId = userId;
        this.passenger = passenger;
        this.flight = flight;
        this.dateOfPurchase = dateOfPurchase;
        this.seatNumber = seatNumber;
    }

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public LocalDateTime getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
