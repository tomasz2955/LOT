package com.example.LOT.repository;


import com.example.LOT.entity.Seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{



}
