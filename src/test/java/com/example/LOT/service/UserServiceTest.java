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
    void getUsers() {

        UserService userService = mock(UserService.class);
        when(userService.getUsers()).thenReturn(List.of(new User("Tomasz", "Bator", "bator@wp.pl", "100200300",
                "qwerty", new ArrayList<>())));
        int result = userService.getUsers().size();
        assertEquals(result, 1,0);

    }
}