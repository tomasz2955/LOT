package com.example.LOT.repository;

import com.example.LOT.entity.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


}
