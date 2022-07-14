package com.example.LOT.integration;

import com.example.LOT.dto.RegisterUserDto;
import com.example.LOT.entity.User;
import com.example.LOT.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles("test")
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldSaveAndGetUserById() throws Exception {
        //given
        RegisterUserDto userDto = new RegisterUserDto("Tomasz_Bator", "email@gmail.com", "555444333", "password");

        //when
        ResultActions saveUserResponse = mockMvc.perform(post("/users/register")
                .content(objectMapper.writeValueAsString(userDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        saveUserResponse.andExpect(status().isOk());

        //when
        String id = saveUserResponse.andReturn().getResponse().getContentAsString();
        ResultActions getUserResponse = mockMvc.perform(get("/users/" + id));

        //then
        getUserResponse.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.name").value("Tomasz"))
                .andExpect(jsonPath("$.lastName").value("Bator"))
                .andExpect(jsonPath("$.email").value("email@gmail.com"))
                .andExpect(jsonPath("$.phoneNumber").value("555444333"))
                .andExpect(jsonPath("$.tickets").isArray());
    }


    @Test
    public void shouldThrowExceptionWhenSavedUserHasWrongEmail() throws Exception {
        //given
        RegisterUserDto userDto = new RegisterUserDto("Tomasz_Bator", "WRONG_EMAIL", "555444333", "password");

        //when
        ResultActions saveUserResponse = mockMvc.perform(post("/users/register")
                .content(objectMapper.writeValueAsString(userDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        saveUserResponse.andExpect(status().isBadRequest()).
                andDo(print())
                .andExpect(jsonPath("$.fieldValidationMessages[0]").value("email - Email should be valid"));
    }

    //TODO update user - zapisać, wziąć id i zrobić update i odczytać

    //TODO update user - przekazać za krótki password i sprawdzić że poleci wyjątek

    @Test
    public void shouldReturnAllUsers() throws Exception {
        //given
        User savedUser1 = userRepository.save(new User( "Janusz", "Kowalski", "email@gmail.com", "555444333", "password",
                Collections.emptyList()));
        User savedUser2 = userRepository.save(new User( "Janusz2", "Kowalski2", "email@gmail.com2", "555444333", "password2",
                Collections.emptyList()));
        //when
        ResultActions response = mockMvc.perform(get("/users"));
        //then
        response.andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$[0].name").value("Janusz")) //TODO zmienne
                .andExpect(jsonPath("$[0].lastName").value("Kowalski"))
                .andExpect(jsonPath("$[0].email").value("email@gmail.com"))
                .andExpect(jsonPath("$[0].phoneNumber").value("555444333"))
                .andExpect(jsonPath("$[0].tickets").isArray())
                .andExpect(jsonPath("$[1].name").value("Janusz2"))
                .andExpect(jsonPath("$[1].lastName").value("Kowalski2"))
                .andExpect(jsonPath("$[1].email").value("email@gmail.com2"))
                .andExpect(jsonPath("$[1].phoneNumber").value("555444333"))
                .andExpect(jsonPath("$[1].tickets").isArray());
    }

}
