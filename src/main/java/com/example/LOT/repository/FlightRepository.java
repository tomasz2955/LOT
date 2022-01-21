package com.example.LOT.repository;

import com.example.LOT.entity.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight findByOriginAndDestination (String origin, String destination); //to powinno zwracac optionala bo nie wiesz czy cos znajdzie czy nie
}
