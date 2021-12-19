package com.example.LOT.repository;

import com.example.LOT.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository {
    Flight findByOriginAndDestination (String origin, String destination);
}
