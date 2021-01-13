package com.unibuc.homemanagementplatform.service;

import com.unibuc.homemanagementplatform.dto.UserRequestCreate;
import com.unibuc.homemanagementplatform.dto.UserRequestGet;
import com.unibuc.homemanagementplatform.mapper.UserMapperCreate;
import com.unibuc.homemanagementplatform.mapper.UserMapperGet;
import com.unibuc.homemanagementplatform.model.Family;
import com.unibuc.homemanagementplatform.model.User;
import com.unibuc.homemanagementplatform.repository.FamilyRepository;
import com.unibuc.homemanagementplatform.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private FamilyRepository familyRepository;

    @Mock
    private UserMapperGet userMapperGet;

    @Mock
    private UserMapperCreate userMapperCreate;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskService taskService;

    @Test
    public void testGetUsersFromFamily() {
        //assign
        Long id = 1L;
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserEmail("delia@yahoo.com");
        users.add(user);

        when(userRepository.getUsers(any())).thenReturn(users);

        List<UserRequestGet> userRequestGets = new ArrayList<>();
        UserRequestGet userRequestGet = new UserRequestGet();
        userRequestGet.setUserEmail("delia@yahoo.com");
        userRequestGets.add(userRequestGet);

        when(userMapperGet.mapToRequest(user)).thenReturn(userRequestGet);
        //act
        List<UserRequestGet> userRequestGets1 = userService.getUsersFromFamily(id);
        //assert
        assertEquals(userRequestGets,userRequestGets1);

    }

    @Test
    public void testCreateUser() {
        //assign
        UserRequestCreate userRequestCreate = new UserRequestCreate();
        userRequestCreate.setUserEmail("delia@yahoo.com");
        userRequestCreate.setPassword("hello");
        userRequestCreate.setName("Delia");
        userRequestCreate.setFamilyId(1);

        User user = new User();
        user.setUserEmail("delia@yahoo.com");
        user.setPassword("hello");
        user.setName("Delia");
        Family family = new Family();
        family.setFamilyId(1L);
        user.setFamily(family);

        when(userMapperCreate.mapToEntity(userRequestCreate)).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);

        UserRequestGet userRequestGet = new UserRequestGet();
        userRequestGet.setUserEmail("delia@yahoo.com");
        userRequestGet.setName("Delia");
        userRequestGet.setFamilyName("Marin");

        when(userMapperGet.mapToRequest(user)).thenReturn(userRequestGet);
        //act
        UserRequestGet userRequestGet1 = userService.createUser(userRequestCreate);
        //assert
        assertEquals(userRequestGet,userRequestGet1);
    }

    @Test
    public void testGetUser() {
        //assign
        String email = "delia@yahoo.com";
        User user = new User();
        user.setUserEmail(email);
        Family family = new Family();
        family.setFamilyId(1L);
        family.setFamilyName("Marin");
        user.setFamily(family);

        when(userRepository.getOne(any())).thenReturn(user);

        UserRequestGet userRequestGet = new UserRequestGet();
        userRequestGet.setUserEmail(email);
        userRequestGet.setFamilyName("Marin");

        when(userMapperGet.mapToRequest(user)).thenReturn(userRequestGet);
        //act
        UserRequestGet userRequestGet1 =userService.getUser(email);
        //assert
        assertEquals(userRequestGet,userRequestGet1);
    }

    @Test
    public void testDelete() {
        //assign
        String email = "delia@yahoo.com";
        when(userRepository.delete(any())).thenReturn(true);
        //act
        boolean deleted = userService.delete(email);
        //assert
        assertTrue(deleted);
    }

    @Test
    public void testGetUserWithPass() {
        String email ="delia@yahoo.com";
        User user = new User();
        user.setPassword("hi");
        when(userRepository.getOneWithPass(any())).thenReturn(user);

        User user1 = userService.getUserWithPass(email);

        assertEquals(user,user1);
    }
}