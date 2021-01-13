package com.unibuc.homemanagementplatform.service;

import com.unibuc.homemanagementplatform.dto.TaskRequestCreate;
import com.unibuc.homemanagementplatform.dto.TaskRequestGet;
import com.unibuc.homemanagementplatform.mapper.TaskMapperCreate;
import com.unibuc.homemanagementplatform.mapper.TaskMapperGet;
import com.unibuc.homemanagementplatform.model.Status;
import com.unibuc.homemanagementplatform.model.StatusValue;
import com.unibuc.homemanagementplatform.model.Task;
import com.unibuc.homemanagementplatform.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    public void testUpdateDescription() {
        //assign
        Long id = 1L;
        Task task = new Task();
        task.setDescription("do laundry");

        Task updatedTask = new Task();
        updatedTask.setDescription("do laundry now");

        when(taskRepository.update(any(),any())).thenReturn(updatedTask);

        TaskRequestCreate taskRequestCreate = new TaskRequestCreate();
        taskRequestCreate.setDescription("do laundry now");

        when(taskMapperCreate.mapToRequest(updatedTask)).thenReturn(taskRequestCreate);

        //act
        TaskRequestCreate taskRequestCreate1 =  taskService.updateDescription(id,task);

        //assert
        assertEquals(taskRequestCreate,taskRequestCreate1);
    }

    @Test
    public void testDelete() {
        //assign
        Long id = 1L;
        when(taskRepository.remove(id)).thenReturn(true);
        //act
        boolean deleted = taskService.delete(id);
        //assert
        assertTrue(deleted);
    }

    @Test
    public void testUpdateStatus() {
        //assign
        Long id = 1L;
        Status status = new Status(1,StatusValue.ASSIGNED);
        Task task = new Task();
        task.setStatus(status);

        Task updatedTask = new Task();
        updatedTask.setStatus(status);

        when(taskRepository.updateStatus(any(),any())).thenReturn(updatedTask);

        TaskRequestGet taskRequestGet = new TaskRequestGet();
        taskRequestGet.setStatus(status);

        when(taskMapperGet.mapToRequest(updatedTask)).thenReturn(taskRequestGet);
        //act
        TaskRequestGet taskRequestGet1 = taskService.updateStatus(id,task);
        //assert
        assertEquals(taskRequestGet,taskRequestGet1);
    }


}