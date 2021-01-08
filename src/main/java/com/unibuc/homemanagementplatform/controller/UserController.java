package com.unibuc.homemanagementplatform.controller;

import com.unibuc.homemanagementplatform.dto.UserRequestCreate;
import com.unibuc.homemanagementplatform.dto.UserRequestGet;
import com.unibuc.homemanagementplatform.mapper.UserMapperGet;
import com.unibuc.homemanagementplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapperGet userMapperGet;

    @PostMapping(path = "/create")
    public ResponseEntity<UserRequestGet> createUser(@RequestBody UserRequestCreate userRequestCreate) {
        long familyId = userRequestCreate.getFamilyId();

        return ResponseEntity.ok().body(userService.createUser(userRequestCreate));
    }


}
