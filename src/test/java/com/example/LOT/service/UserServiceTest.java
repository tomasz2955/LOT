package com.example.LOT.service;

import com.example.LOT.entity.User;
import com.example.LOT.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mapstruct.control.MappingControl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Test
    void shouldGetUsers() {

        //mock userRepository
        //mock ticketRepository

        when( userRepository.findAll() => List<User>)

        UserService userService = new UserService(userRepo, ticketRepo);

        int result = userService.getUsers().size();
        assertEquals(result, 1);
    }

    @Test
    void shouldFindById() {
        //mock userRepository
        //mock ticketRepository

        UserService userService = new UserService(userRepo, ticketRepo);

        when(userRepository.findById(id) => User)

        userService.findById()
    }

    @Test
    void shouldThrowExceptionWhenFindByIdFoundNothing() {
        //mock userRepository
        //mock ticketRepository

        UserService userService = new UserService(userRepo, ticketRepo);

        when(userRepository.findById(id) => null)

    }

}
