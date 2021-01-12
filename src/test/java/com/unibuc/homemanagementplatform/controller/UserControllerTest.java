package com.unibuc.homemanagementplatform.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.homemanagementplatform.dto.UserRequestCreate;
import com.unibuc.homemanagementplatform.dto.UserRequestGet;
import com.unibuc.homemanagementplatform.model.User;
import com.unibuc.homemanagementplatform.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateUser() throws Exception {
        //Arrange
        UserRequestCreate userRequestCreate = new UserRequestCreate();
        userRequestCreate.setFamilyId(1);
        userRequestCreate.setName("Delia");
        userRequestCreate.setPassword("hello");
        userRequestCreate.setUserEmail("dell@gmail.com");

        UserRequestGet userRequestGet = new UserRequestGet();
        userRequestGet.setName("Delia");
        userRequestGet.setFamilyName("my family");
        userRequestGet.setUserEmail("dell@gmail.com");

        when(userService.createUser(any())).thenReturn(userRequestGet);
        //Act
        MvcResult result = mockMvc
                .perform(post("/user/create")
                        .content(objectMapper.writeValueAsString(userRequestCreate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //Assert
        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(userRequestGet));

    }

    @Test
    public void testAuthUser() throws Exception {
        User user = new User();
        user.setUserEmail("del@gmail.com");
        user.setPassword("hi");

        when(userService.getUserWithPass(any())).thenReturn(user);

        MvcResult result = mockMvc
                .perform(post("/user/login")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(0));

    }

    @Test
    public void testGetUser() throws Exception {
        String email = "delia@gmail";
        UserRequestGet userRequestGet = new UserRequestGet();
        userRequestGet.setUserEmail("delia@gmail");
        userRequestGet.setName("Delia");
        userRequestGet.setFamilyName("Chirigiu");

        when(userService.getUser(any())).thenReturn(userRequestGet);

        MvcResult result = mockMvc.perform(get("/user/" + email))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(userRequestGet));

    }

    @Test
    public void testDeleteUser() throws Exception {
        String email = "delia@gmail";

        when(userService.delete(any())).thenReturn(true);

        MvcResult mvcResult = mockMvc
                .perform(delete("/user/delete/" + email))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(email);

    }
}