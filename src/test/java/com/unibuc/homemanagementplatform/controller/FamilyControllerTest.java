package com.unibuc.homemanagementplatform.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.homemanagementplatform.dto.FamilyRequestCreate;
import com.unibuc.homemanagementplatform.dto.FamilyRequestGet;
import com.unibuc.homemanagementplatform.mapper.FamilyCreateMapper;
import com.unibuc.homemanagementplatform.model.Family;
import com.unibuc.homemanagementplatform.model.User;
import com.unibuc.homemanagementplatform.service.FamilyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FamilyControllerTest {


    @Mock
    private FamilyService familyService;

    @Mock
    private FamilyCreateMapper familyCreateMapper;

    @InjectMocks
    private FamilyController familyController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(familyController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateFamily() throws Exception {
        //Arrange
        FamilyRequestCreate familyRequestCreate = new FamilyRequestCreate();
        familyRequestCreate.setFamilyName("Marin");

        Family family = new Family();
        family.setFamilyName("Marin");

        Family createdFamily = new Family();
        createdFamily.setFamilyName("Marin");

        when(familyCreateMapper.mapToEntity(any())).thenReturn(family);
        when(familyService.createFamily(any())).thenReturn(createdFamily);

        //act
        MvcResult result = mockMvc
                .perform(post("/family/create")
                        .content(objectMapper.writeValueAsString(familyRequestCreate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(createdFamily));
    }

    @Test
    public void testGetOne() throws Exception {
        // Arrange
        Long familyId = 1L;
        FamilyRequestGet familyRequestGet = new FamilyRequestGet();
        familyRequestGet.setFamilyId(familyId);
        familyRequestGet.setFamilyName("Marin");

        when(familyService.getOne(familyId)).thenReturn(familyRequestGet);

        // Act
        MvcResult result = mockMvc.perform(get("/family/" + familyId))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(familyRequestGet));

    }

}