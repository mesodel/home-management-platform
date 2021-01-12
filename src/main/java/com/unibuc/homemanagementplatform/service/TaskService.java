package com.unibuc.homemanagementplatform.service;

import com.unibuc.homemanagementplatform.dto.TaskRequestCreate;
import com.unibuc.homemanagementplatform.dto.TaskRequestGet;
import com.unibuc.homemanagementplatform.dto.UserRequestTaskCreate;
import com.unibuc.homemanagementplatform.mapper.TaskMapperCreate;
import com.unibuc.homemanagementplatform.mapper.TaskMapperGet;
import com.unibuc.homemanagementplatform.model.Task;
import com.unibuc.homemanagementplatform.model.User;
import com.unibuc.homemanagementplatform.repository.TaskRepository;
import com.unibuc.homemanagementplatform.repository.User_Task_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapperGet taskMapperGet;

    @Autowired
    private TaskMapperCreate taskMapperCreate;

    @Autowired
    private User_Task_Repository user_task_repository;


    public List<TaskRequestGet> getTasksOfUser(String email) {
       List<TaskRequestGet> tasksDto = new ArrayList<>();
       List<Task> tasks = taskRepository.getTasksOfUser(email);
       for(Task t : tasks) {
           tasksDto.add(taskMapperGet.mapToRequest(t));
       }
       return tasksDto;
    }

    public TaskRequestCreate create(TaskRequestCreate t) {
        Task task = taskMapperCreate.mapToEntity(t);
        task.setUsers(t.getAssignedTo());
        taskRepository.save(task);

        return t;
    }

    public TaskRequestCreate updateDescription(Long id, Task taskReq) {
        Task task = taskRepository.update(id,taskReq.getDescription());

        TaskRequestCreate taskRequestCreate = taskMapperCreate.mapToRequest(task);

        List<User> users = user_task_repository.getUsers(id);
        List<UserRequestTaskCreate> usersTask = new ArrayList<>();
        for(User u : users) {
            UserRequestTaskCreate userTask = new UserRequestTaskCreate(u.getUserEmail());
            usersTask.add(userTask);
        }
        taskRequestCreate.setUsers(usersTask);

        return taskRequestCreate;
    }

    public boolean delete(Long id) {
        return taskRepository.remove(id);
    }

    public TaskRequestGet updateStatus(Long id, Task taskReq) {
        Task task = taskRepository.updateStatus(id,taskReq.getStatus());
        TaskRequestGet taskRequestGet = taskMapperGet.mapToRequest(task);
        taskRequestGet.setStatus(task.getStatus());

        return taskRequestGet;
    }
}
