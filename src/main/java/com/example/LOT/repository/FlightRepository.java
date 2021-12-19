package com.example.LOT.repository;

import com.example.LOT.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository { //tutaj cos krzyczy na zółto, chyba czegoś brakuje
    Flight findByOriginAndDestination (String origin, String destination); //poprosiłbym endpoint do tego
    // powyższe powinno zwrócić listę lotów bo przecież po origin i destination zwróci ich wiele
}
