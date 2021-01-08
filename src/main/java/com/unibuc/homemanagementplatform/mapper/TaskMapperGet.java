package com.unibuc.homemanagementplatform.mapper;

import com.unibuc.homemanagementplatform.dto.TaskRequestGet;
import com.unibuc.homemanagementplatform.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TaskMapperGet {

    TaskRequestGet mapToRequest(Task task);

    Task mapToEntity(TaskRequestGet taskRequestGet);
}
