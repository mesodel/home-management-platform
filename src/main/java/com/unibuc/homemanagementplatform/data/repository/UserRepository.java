package com.unibuc.homemanagementplatform.data.repository;

import com.unibuc.homemanagementplatform.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

}
