package com.example.LOT.mapper;

import com.example.LOT.dto.UserTicketsDto;
import com.example.LOT.entity.User;
import org.modelmapper.ModelMapper;

import java.util.Collections;

public class UserTicketsMapper {

    private final ModelMapper mapper;

    public UserTicketsMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UserTicketsDto mapToUserTicketsDto(User user) {
        //uzyj modelMappera do przemapowania do dto identycznego jak nasza encja
        List<TicketDto> ticketDtos = Collections.emptyList()
        
        return new UserTicketsDto(user.getId(), ticketDtos);
    }
}
