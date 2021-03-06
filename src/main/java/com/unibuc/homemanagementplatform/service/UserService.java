package com.unibuc.homemanagementplatform.service;

import com.unibuc.homemanagementplatform.dto.UserRequestCreate;
import com.unibuc.homemanagementplatform.dto.UserRequestGet;
import com.unibuc.homemanagementplatform.mapper.UserMapperCreate;
import com.unibuc.homemanagementplatform.mapper.UserMapperGet;
import com.unibuc.homemanagementplatform.model.Family;
import com.unibuc.homemanagementplatform.model.User;
import com.unibuc.homemanagementplatform.repository.FamilyRepository;
import com.unibuc.homemanagementplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private UserMapperGet userMapperGet;

    @Autowired
    private UserMapperCreate userMapperCreate;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TaskService taskService;

    public List<UserRequestGet> getUsersFromFamily(Long familyId) {
        List<User> users = repository.getUsers(familyId);

        List<UserRequestGet> usersDTO = new ArrayList<>();

        for(User u : users) {
            usersDTO.add(userMapperGet.mapToRequest(u));
        }

        return usersDTO;
    }

    public UserRequestGet createUser(UserRequestCreate userRequestCreate) {
        User user = userMapperCreate.mapToEntity(userRequestCreate);
        repository.save(user);
        Family family = familyRepository.getOne(userRequestCreate.getFamilyId());
        user.setFamily(family);

        return userMapperGet.mapToRequest(user);
    }

    public UserRequestGet getUser(String email) {
        User user = repository.getOne(email);
        user.setFamily(familyRepository.getOne(user.getFamily().getFamilyId()));

        UserRequestGet userRequestGet = userMapperGet.mapToRequest(user);
        userRequestGet.setTasks(taskService.getTasksOfUser(email));
        return userRequestGet;
    }

    public boolean delete(String email) {
        return repository.delete(email);
    }

    public User getUserWithPass(String email) {
        return repository.getOneWithPass(email);
    }
}
