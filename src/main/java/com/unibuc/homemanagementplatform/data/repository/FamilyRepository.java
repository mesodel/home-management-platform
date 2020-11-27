package com.unibuc.homemanagementplatform.data.repository;

import com.unibuc.homemanagementplatform.data.entity.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends CrudRepository<Family,Long> {

}
