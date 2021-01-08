package com.unibuc.homemanagementplatform.mapper;

import com.unibuc.homemanagementplatform.dto.FamilyRequestGet;
import com.unibuc.homemanagementplatform.model.Family;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FamilyGetMapper {

    @Mappings({
            @Mapping(target = "users", source = "family.familyMembers")
    })
    FamilyRequestGet mapToRequest(Family family);


    @Mappings({
            @Mapping(target = "familyMembers", source = "dto.users")
    })
    Family mapToEntity(FamilyRequestGet dto);
}
