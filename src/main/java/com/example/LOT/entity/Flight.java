package com.example.LOT.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private String origin; //origin i destination moze byc tym samym enumem
    private String destination;
    private String airline; //zrobilbym z tego enuma z racji tego ze jest pewna ograniczona pula linii lotniczych
    private Date date;
    //potrzebuje pola ticketTotalAmount do okreslenia ile biletów jest na dany lot i ticketAmountLeft który powie nam ile biletów zostało

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



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}


