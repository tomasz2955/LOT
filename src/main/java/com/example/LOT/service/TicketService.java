package com.example.LOT.service;

import com.example.LOT.dto.UserTicketsDto;
import com.example.LOT.entity.Ticket;
import com.example.LOT.entity.User;
import com.example.LOT.mapper.UserTicketsMapper;
import com.example.LOT.repository.TicketRepository;
import com.example.LOT.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final UserTicketsMapper userTicketsMapper = new UserTicketsMapper(mapper);

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }


    public UserTicketsDto getTicketsByUserId(String userId) {
        //wyciągnij usera na podstawie id
        User user = null;
        // walidacja czy istnieje itp
        //mapowanko
        return userTicketsMapper.mapToUserTicketsDto(user);
    }


}
