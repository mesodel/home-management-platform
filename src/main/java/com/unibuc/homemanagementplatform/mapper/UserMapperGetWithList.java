package com.unibuc.homemanagementplatform.mapper;

import com.unibuc.homemanagementplatform.dto.UserRequestGetWithList;
import com.unibuc.homemanagementplatform.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapperGetWithList {
    @Mappings(
            {
                    @Mapping(target = "familyName", source = "family.familyName"),
                    @Mapping(target = "tasks", source = "user.tasks")
            }
    )
    UserRequestGetWithList mapToRequest(User user);

    @Mappings(
            {
                    @Mapping(target = "family.familyName", source = "familyName"),
                    @Mapping(target = "user.tasks", source = "tasks")
            }
    )
    User mapToREntity(UserRequestGetWithList user);
}
