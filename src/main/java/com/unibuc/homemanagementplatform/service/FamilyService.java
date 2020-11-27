package com.unibuc.homemanagementplatform.service;

import com.unibuc.homemanagementplatform.data.entity.Family;
import com.unibuc.homemanagementplatform.data.entity.User;
import com.unibuc.homemanagementplatform.data.repository.FamilyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FamilyService {

    private final FamilyRepository familyRepository;

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public Family createFamily(Family family) {
        return familyRepository.save(family);
    }


}
