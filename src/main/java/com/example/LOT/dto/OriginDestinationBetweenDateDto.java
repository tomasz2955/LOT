package com.example.LOT.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OriginDestinationBetweenDateDto {
    private String origin;
    private String destination;
    private LocalDateTime departureDateStart;
    private LocalDateTime departureDateEnd;

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

    public LocalDateTime getDepartureDateStart() {
        return departureDateStart;
    }

    public void setDepartureDateStart(LocalDateTime departureDateStart) {
        this.departureDateStart = departureDateStart;
    }

    public LocalDateTime getDepartureDateEnd() {
        return departureDateEnd;
    }

    public void setDepartureDateEnd(LocalDateTime departureDateEnd) {
        this.departureDateEnd = departureDateEnd;
    }

    public OriginDestinationBetweenDateDto(String origin, String destination, LocalDateTime departureDateStart, LocalDateTime departureDateEnd) {
        this.origin = origin;
        this.destination = destination;
        this.departureDateStart = departureDateStart;
        this.departureDateEnd = departureDateEnd;
    }

    public OriginDestinationBetweenDateDto() {
    }
}
