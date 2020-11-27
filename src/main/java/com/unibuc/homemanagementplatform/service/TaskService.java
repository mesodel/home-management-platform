package com.unibuc.homemanagementplatform.service;

import com.unibuc.homemanagementplatform.data.entity.Task;
import com.unibuc.homemanagementplatform.data.repository.TaskRepository;
import com.unibuc.homemanagementplatform.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task saveTask(Task task) {

        userRepository.save(userRepository.findAllById(task.getUser().getUserEmail()));
        return taskRepository.save(task);
    }


}
