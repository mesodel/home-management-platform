package com.unibuc.homemanagementplatform.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.homemanagementplatform.dto.TaskRequestCreate;
import com.unibuc.homemanagementplatform.dto.TaskRequestGet;
import com.unibuc.homemanagementplatform.dto.UserRequestTaskCreate;
import com.unibuc.homemanagementplatform.model.Status;
import com.unibuc.homemanagementplatform.model.StatusValue;
import com.unibuc.homemanagementplatform.model.Task;
import com.unibuc.homemanagementplatform.model.User;
import com.unibuc.homemanagementplatform.service.TaskService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateTask() throws Exception {
        //Arrange
        List<UserRequestTaskCreate> users = new ArrayList<>();
        UserRequestTaskCreate user = new UserRequestTaskCreate("delia.chirigiu@gmail.com");
        users.add(user);

        TaskRequestCreate taskRequestCreate = new TaskRequestCreate();
        taskRequestCreate.setName("laundry");
        taskRequestCreate.setDescription("do laundry");
        taskRequestCreate.setDueBy(new Date(2021, Calendar.JANUARY,12));
        taskRequestCreate.setUsers(users);

        when(taskService.create(any())).thenReturn(taskRequestCreate);
        //Act
        MvcResult result = mockMvc
                .perform(post("/task/create")
                        .content(objectMapper.writeValueAsString(taskRequestCreate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(taskRequestCreate));

    }

    @Test
    public void testUpdateTaskDescription() throws Exception {
        //Arrange
        Long id = 1L;
        Task task = new Task();
        task.setTaskId(1L);
        task.setName("laundry");
        task.setDescription("do laundry");

        TaskRequestCreate taskRequestCreate = new TaskRequestCreate();
        taskRequestCreate.setName("laundry");
        taskRequestCreate.setDescription("do laundry");

        when(taskService.updateDescription(any(), any())).thenReturn(taskRequestCreate);

        //Act
        MvcResult result = mockMvc
                .perform(put("/task/updateDescription/" + id)
                        .content(objectMapper.writeValueAsString(task))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(taskRequestCreate));

    }

    @Test
    public void testDeleteTask() throws Exception {
        Long id = 1L;

        when(taskService.delete(any())).thenReturn(true);

        MvcResult mvcResult = mockMvc
                .perform(delete("/task/delete/"+id))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(id));

    }

    @Test
    public void testUpdateStatus() throws Exception {
        Long id = 1L;
        Status status = new Status();

        status.setStatusValue(StatusValue.ASSIGNED);
        status.setStatusId(1);
        Task task = new Task();
        task.setName("laundry");
        task.setStatus(status);
        task.setDescription("do laundry");
        task.setTaskId(id);

        TaskRequestGet taskRequestGet = new TaskRequestGet();
        taskRequestGet.setTaskId(id);
        taskRequestGet.setName("laundry");
        taskRequestGet.setDescription("do laundry");
        taskRequestGet.setStatus(status);


        when(taskService.updateStatus(any(),any())).thenReturn(taskRequestGet);

        MvcResult result = mockMvc
                .perform(put("/task/updateStatus/"+id)
                        .content(objectMapper.writeValueAsString(task))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(taskRequestGet));

    }

}