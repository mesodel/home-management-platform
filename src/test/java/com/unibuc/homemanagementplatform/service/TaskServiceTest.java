package com.unibuc.homemanagementplatform.service;

import com.unibuc.homemanagementplatform.dto.TaskRequestCreate;
import com.unibuc.homemanagementplatform.dto.TaskRequestGet;
import com.unibuc.homemanagementplatform.mapper.TaskMapperCreate;
import com.unibuc.homemanagementplatform.mapper.TaskMapperGet;
import com.unibuc.homemanagementplatform.model.Status;
import com.unibuc.homemanagementplatform.model.StatusValue;
import com.unibuc.homemanagementplatform.model.Task;
import com.unibuc.homemanagementplatform.repository.TaskRepository;
import com.unibuc.homemanagementplatform.repository.User_Task_Repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapperGet taskMapperGet;

    @Mock
    private TaskMapperCreate taskMapperCreate;

    @Mock
    private User_Task_Repository user_task_repository;

    @Test
    public void testGetTasksOfUser() {

        //arrange
        String email = "delia98@yahoo.com";

        List<TaskRequestGet> taskRequestGets = new ArrayList<>();

        TaskRequestGet task = new TaskRequestGet();
        task.setStatus(new Status(1, StatusValue.ASSIGNED));
        task.setDescription("do laundry");
        task.setTaskId(1L);
        task.setName("laundry");
        taskRequestGets.add(task);

        List<Task> tasks = new ArrayList<>();
        Task t = new Task();
        t.setTaskId(1L);
        t.setName("laundry");
        t.setDescription("do laundry");
        t.setStatus(new Status(1, StatusValue.ASSIGNED));
        tasks.add(t);

        when(taskRepository.getTasksOfUser(email)).thenReturn(tasks);
        when(taskMapperGet.mapToRequest(any())).thenReturn(task);

        //act
        List<TaskRequestGet> taskRequestGet1 = taskService.getTasksOfUser(email);

        //assert
        verify(taskMapperGet, times(1)).mapToRequest(t);
        assertEquals(taskRequestGets, taskRequestGet1);
    }

    @Test
    public void testCreate() {
        //assign
        TaskRequestCreate taskRequestCreate = new TaskRequestCreate();
        taskRequestCreate.setDescription("wash dishes");
        taskRequestCreate.setName("dishes");

        Task task = new Task();
        task.setDescription("wash dishes");
        task.setName("dishes");

        when(taskMapperCreate.mapToEntity(any())).thenReturn(task);

        Task savedTask = new Task();
        savedTask.setTaskId(1L);
        savedTask.setName("dishes");
        savedTask.setDescription("do dishes");

        when(taskRepository.save(any())).thenReturn(savedTask);

        //act
        TaskRequestCreate taskRequestCreate1 = taskService.create(taskRequestCreate);

        //assert
        assertEquals(taskRequestCreate, taskRequestCreate1);
    }


}