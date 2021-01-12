package com.unibuc.homemanagementplatform.service;

import com.unibuc.homemanagementplatform.dto.FamilyRequestGet;
import com.unibuc.homemanagementplatform.dto.UserRequestGet;
import com.unibuc.homemanagementplatform.mapper.FamilyGetMapper;
import com.unibuc.homemanagementplatform.model.Family;
import com.unibuc.homemanagementplatform.repository.FamilyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FamilyServiceTest {

    @InjectMocks
    private FamilyService familyService;

    @Mock
    private FamilyRepository familyRepository;

    @Mock
    private FamilyGetMapper getMapper;

    @Mock
    private UserService userService;

    @Test
    public void  testCreateFamily() {
        //Arrange
        Family family = new Family();
        family.setFamilyName("Marin");

        when(familyRepository.save(any())).thenReturn(family);

        //Act
        Family family1 = familyService.createFamily(family);

        //Assert
        verify(familyRepository,times(1)).save(family);
        assertEquals(family1, family);
    }

    @Test
    public void testGetOne() {
        //Arrange
        long familyId = 1;
        Family family = new Family();
        family.setFamilyId(familyId);
        family.setFamilyName("Marin");

        List<UserRequestGet> users = new ArrayList<>();
        UserRequestGet user = new UserRequestGet();
        user.setFamilyName("Marin");
        user.setName("Ion");

        FamilyRequestGet familyRequestGet = new FamilyRequestGet();
        familyRequestGet.setFamilyName("Marin");
        familyRequestGet.setFamilyId(familyId);
        familyRequestGet.setUsers(users);

        when(getMapper.mapToRequest(family)).thenReturn(familyRequestGet);
        when(familyRepository.getOne(familyId)).thenReturn(family);
        when(userService.getUsersFromFamily(any())).thenReturn(users);

        //Act
        FamilyRequestGet familyRequestGet1 = familyService.getOne(familyId);

        //Assert
        assertThat(familyRequestGet1).isNotNull();

        verify(userService,times(1)).getUsersFromFamily(familyId);
        assertEquals(familyRequestGet1, familyRequestGet);
    }

}