package com.unibuc.homemanagementplatform.data.repository;

import com.unibuc.homemanagementplatform.data.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {

}
