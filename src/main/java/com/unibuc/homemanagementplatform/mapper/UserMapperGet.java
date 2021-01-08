package com.unibuc.homemanagementplatform.mapper;

import com.unibuc.homemanagementplatform.dto.UserRequestGet;
import com.unibuc.homemanagementplatform.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapperGet {

    @Mappings(
            {
                    @Mapping(target = "familyName", source = "family.familyName")
            }
    )
    UserRequestGet mapToRequest(User user);

    @Mappings(
            {
                    @Mapping(target = "family.familyName", source = "familyName")
            }
    )
    User mapToEntity(UserRequestGet userRequestGet);
}
