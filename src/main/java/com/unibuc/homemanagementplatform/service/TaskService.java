package com.unibuc.homemanagementplatform.service;

import com.unibuc.homemanagementplatform.dto.TaskRequestCreate;
import com.unibuc.homemanagementplatform.dto.TaskRequestGet;
import com.unibuc.homemanagementplatform.mapper.TaskMapperCreate;
import com.unibuc.homemanagementplatform.mapper.TaskMapperGet;
import com.unibuc.homemanagementplatform.model.Task;
import com.unibuc.homemanagementplatform.repository.TaskRepository;
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
}
