package com.unibuc.homemanagementplatform.controller;

import com.unibuc.homemanagementplatform.dto.FamilyRequestCreate;
import com.unibuc.homemanagementplatform.dto.FamilyRequestGet;
import com.unibuc.homemanagementplatform.mapper.FamilyCreateMapper;
import com.unibuc.homemanagementplatform.model.Family;
import com.unibuc.homemanagementplatform.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/family")
public class FamilyController {
    @Autowired
    private FamilyCreateMapper familyCreateMapper;

    @Autowired
    private FamilyService familyService;

    @PostMapping(path = "/create")
    public ResponseEntity<Family> createFamily(@RequestBody FamilyRequestCreate familyRequest) {
        Family family = familyCreateMapper.mapToEntity(familyRequest);
        Family createdFamily = familyService.createFamily(family);
        return ResponseEntity.ok().body(createdFamily);
    }

    @GetMapping("/{familyId}")
    public ResponseEntity<FamilyRequestGet> getFamily(@PathVariable("familyId") long familyId) {
        FamilyRequestGet family = familyService.getOne(familyId);

        return ResponseEntity.ok().body(family);
    }
}
