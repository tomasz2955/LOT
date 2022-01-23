package com.example.LOT.service;

import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.entity.Flight;
import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import com.example.LOT.repository.FlightRepository;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {


    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;


    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, FlightRepository flightRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public void buyTicket(BuyingTicketDto buyingTicketDto) {
        User findUser = userRepository.findById(buyingTicketDto.getUserId()).orElseThrow(); //orElseThrow musi wszędzie rzucac jakis exception z message który mi cos powie na frontendzie
        Flight findFlight = flightRepository.findById(buyingTicketDto.getFlightId()).orElseThrow();
        //podczas tworzenia encji flight powinienes ustawiac ilosc dostępnych biletów w locie. Podczas kupowania biletów powinienes
        //odejmowac tą ilość z lotu. Gdy lot nie ma juz biletów powinieneś rzucić exception z odpowiednim message
        ticketRepository.save(new Ticket(findUser, findFlight, LocalDateTime.now()));
    }

    //usuwanie biletu na podstawie ID
    public void deleteById(Long id) {
        Ticket findTicket = ticketRepository.findById(id).orElseThrow();
        LocalDateTime from = findTicket.getFlight().getDepartureDate();
        LocalDateTime to = LocalDateTime.now();
        Duration duration = Duration.between(to, from);
        if(duration.toHours() <=24) {
            throw new RuntimeException("You can not return ticket");
        } else {
            ticketRepository.deleteById(id);
        }
    }
}
