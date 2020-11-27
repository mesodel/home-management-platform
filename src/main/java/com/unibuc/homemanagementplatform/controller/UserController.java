package com.unibuc.homemanagementplatform.controller;

import com.unibuc.homemanagementplatform.data.entity.Family;
import com.unibuc.homemanagementplatform.data.entity.User;
import com.unibuc.homemanagementplatform.service.FamilyService;
import com.unibuc.homemanagementplatform.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public int authUser(@RequestBody User user) {
        User myUser = userService.authUser(user.getUserEmail())
                .orElse(null);
        if (myUser != null) {
            if (user.getPassword().equals(myUser.getPassword())) {
                return 0; //auth succeeded
            } else {
                return -2; //wrong password
            }
        } else {
            return -1; //user not found
        }
    }
}
