package com.unibuc.homemanagementplatform.service;

import com.unibuc.homemanagementplatform.data.entity.Family;
import com.unibuc.homemanagementplatform.data.entity.User;
import com.unibuc.homemanagementplatform.data.repository.FamilyRepository;
import com.unibuc.homemanagementplatform.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;

    public UserService(UserRepository userRepository, FamilyRepository familyRepository) {
        this.userRepository = userRepository;
        this.familyRepository = familyRepository;
    }

    public void createUser(User user) {
        Family family = familyRepository.findById(user.getFamily().getFamilyId()).orElse(null);
        familyRepository.save(familyRepository.findById(user.getFamily().getFamilyId()).orElse(null));
        user.setFamily(family);
        userRepository.save(user);
    }

    public Optional<User> authUser(String email) {
        return userRepository.findById(email);
    }
}
