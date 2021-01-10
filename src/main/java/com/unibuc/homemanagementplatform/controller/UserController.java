package com.unibuc.homemanagementplatform.controller;

import com.unibuc.homemanagementplatform.dto.UserRequestCreate;
import com.unibuc.homemanagementplatform.dto.UserRequestGet;
import com.unibuc.homemanagementplatform.mapper.UserMapperGet;
import com.unibuc.homemanagementplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/create")
    public ResponseEntity<UserRequestGet> createUser(@RequestBody UserRequestCreate userRequestCreate) {
        return ResponseEntity.ok().body(userService.createUser(userRequestCreate));
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<UserRequestGet> getUser(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(userService.getUser(email));
    }

    @DeleteMapping(path = "delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        boolean result = userService.delete(email);
        if(!result) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(email, HttpStatus.OK);
    }
}
