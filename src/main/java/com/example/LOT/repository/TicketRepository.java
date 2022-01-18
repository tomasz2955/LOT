package com.example.LOT.repository;

import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User; // tam gdzie nie uzywane to trzeba wyrzucic
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // tam gdzie nie uzywane to trzeba wyrzucic
// to tak jakbyś oddawał mieszkanie na sprzedaż i zostawił kilka papierków po batonikach na ziemi

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


}
