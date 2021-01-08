package com.unibuc.homemanagementplatform.service;

import com.unibuc.homemanagementplatform.dto.FamilyRequestGet;
import com.unibuc.homemanagementplatform.mapper.FamilyGetMapper;
import com.unibuc.homemanagementplatform.model.Family;
import com.unibuc.homemanagementplatform.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyGetMapper getMapper;

    @Autowired
    private UserService userService;

    public Family createFamily(Family family) {
        return familyRepository.save(family);
    }


    public FamilyRequestGet getOne(long familyId) {
        Family family = familyRepository.getOne(familyId);
        FamilyRequestGet dto = getMapper.mapToRequest(family);
        dto.setUsers(userService.getUsersFromFamily(familyId));

        return dto;
    }
}
