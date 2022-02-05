package com.example.LOT.dto;

import com.example.LOT.entity.Passenger;
import com.example.LOT.entity.Ticket;

import java.util.List;

public class BuyingTicketDto {
    private Long userId;
    private Long flightId;
    private List<Passenger> passengers;

    public BuyingTicketDto(Long userId, Long flightId, List<Passenger> passengers) {
        this.userId = userId;
        this.flightId = flightId;
        this.passengers = passengers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public BuyingTicketDto() {
    }
}
