package com.example.LOT.service;

import com.example.LOT.dto.BuyingTicketDto;
import com.example.LOT.dto.RegisterUserDto;
import com.example.LOT.dto.UserTicketsDto;
import com.example.LOT.entity.Flight;
import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import com.example.LOT.repository.FlightRepository;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional; //nieuzyte mozna wwyrzucic


@Service
public class TicketService {


    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final ModelMapper mapper = new ModelMapper(); // nieuzyty mozna wyrzucic


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
        ticketRepository.save(new Ticket(findUser, findFlight, LocalDateTime.now(), 9000.25)); // kazdy bilet kosztuje 9000?
        // Może flight powinien zawierać informację o tym ile kosztuje bilet (zakładając na razie jednakową cenę dla wszystkich biletów
    }

    public void deleteById(Long id) {
        Ticket findTicket = ticketRepository.findById(id).orElseThrow(); //exception z message
        if(findTicket.getFlight().getDate().isEqual(LocalDate.now())) {
            throw new RuntimeException("You can not return ticket");
        } else {

            ticketRepository.deleteById(id);
        }
    }






}
