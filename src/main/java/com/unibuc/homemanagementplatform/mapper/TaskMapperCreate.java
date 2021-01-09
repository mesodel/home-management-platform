package com.unibuc.homemanagementplatform.mapper;

import com.unibuc.homemanagementplatform.dto.TaskRequestCreate;
import com.unibuc.homemanagementplatform.dto.UserRequestCreate;
import com.unibuc.homemanagementplatform.model.Task;
import com.unibuc.homemanagementplatform.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper(componentModel = "spring")
public interface TaskMapperCreate {
    TaskRequestCreate mapToRequest(Task task);

    Task mapToEntity(TaskRequestCreate taskRequestCreate);
}
