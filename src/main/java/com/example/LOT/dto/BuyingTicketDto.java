package com.example.LOT.dto;

import com.example.LOT.entity.Passenger;


import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BuyingTicketDto {
    @NotEmpty
    private Long userId;
    @NotEmpty
    private Long flightId;
    @NotEmpty
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
