package com.example.LOT.repository;


import com.example.LOT.entity.Seat;
import com.example.LOT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{

    Optional<Seat> findBySeatNumber (String seatNumber);

}
