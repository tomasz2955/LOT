package com.example.LOT.integration;

import com.example.LOT.entity.User;
import com.example.LOT.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldReturnUserById() throws Exception {
        //given
        User savedUser = userRepository.save(new User( "Janusz", "Kowalski", "email@gmail.com", "555444333", "password",
                Collections.emptyList()));
        //when
        ResultActions response = mockMvc.perform(get("/users/" + savedUser.getId()));
        //then
        response.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.name").value("Janusz"))
                .andExpect(jsonPath("$.lastName").value("Kowalski"))
                .andExpect(jsonPath("$.email").value("email@gmail.com"))
                .andExpect(jsonPath("$.phoneNumber").value("555444333"))
                .andExpect(jsonPath("$.tickets").isArray());
    }

//    @Test
//    public void shouldReturnAllUsers() throws Exception {
//        //given
//        User savedUser1 = userRepository.save(new User( "Janusz", "Kowalski", "email@gmail.com", "555444333", "password",
//                Collections.emptyList()));
//        User savedUser2 = userRepository.save(new User( "Janusz2", "Kowalski2", "email@gmail.com2", "555444333", "password2",
//                Collections.emptyList()));
//        //when
//        ResultActions response = mockMvc.perform(get("/users"));
//        //then
//        response.andExpect(status().isOk()).andDo(print())
//                .andExpect(jsonPath("$[0].name").value("Janusz"))
//                .andExpect(jsonPath("$[0].lastName").value("Kowalski"))
//                .andExpect(jsonPath("$[0].email").value("email@gmail.com"))
//                .andExpect(jsonPath("$[0].phoneNumber").value("555444333"))
//                .andExpect(jsonPath("$[0].tickets").isArray());
//    }

}
