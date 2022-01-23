package com.example.LOT.repository;

import com.example.LOT.entity.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOriginAndDestination (String origin, String destination);

    List<Flight> findByOriginAndDestinationAndDepartureDateBetween(String origin, String destination, LocalDateTime departureDateStart, LocalDateTime departureDateEnd);


}
