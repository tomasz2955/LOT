package com.example.LOT.repository;

import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


}
