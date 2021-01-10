package com.unibuc.homemanagementplatform.controller;

import com.unibuc.homemanagementplatform.dto.TaskRequestCreate;
import com.unibuc.homemanagementplatform.dto.TaskRequestGet;
import com.unibuc.homemanagementplatform.model.Task;
import com.unibuc.homemanagementplatform.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<TaskRequestCreate> createTask(@RequestBody TaskRequestCreate t) {
        taskService.create(t);
        return ResponseEntity.ok().body(t);
    }

    @PutMapping("/updateDescription/{id}")
    public ResponseEntity<TaskRequestCreate> updateTaskDescription(@PathVariable Long id, @RequestBody Task taskReq) {
        TaskRequestCreate task = taskService.updateDescription(id,taskReq);
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id) {
        boolean isRemoved = taskService.delete(id);
        if(!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<TaskRequestGet> updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        TaskRequestGet taskRequestGet = taskService.updateStatus(id,task);
        return ResponseEntity.ok().body(taskRequestGet);
    }
}
