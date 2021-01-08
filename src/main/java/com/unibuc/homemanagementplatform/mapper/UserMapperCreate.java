package com.unibuc.homemanagementplatform.mapper;

import com.unibuc.homemanagementplatform.dto.UserRequestCreate;
import com.unibuc.homemanagementplatform.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapperCreate {
    @Mappings({
            @Mapping(target = "familyId",source="family.familyId")
    })
    UserRequestCreate mapToRequest(User user);

    @Mappings({
            @Mapping(target="family.familyId",source="familyId")
    })
    User mapToEntity(UserRequestCreate userRequestCreate);
}
