package com.unibuc.homemanagementplatform.mapper;

import com.unibuc.homemanagementplatform.dto.FamilyRequestCreate;
import com.unibuc.homemanagementplatform.model.Family;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FamilyCreateMapper {
    @Mappings(
            {
                    @Mapping(target = "familyName", source = "family.familyName")
            }
    )
    FamilyRequestCreate mapToRequest(Family family);

    @Mappings(
            {
                    @Mapping(target = "familyName", source = "familyRequestCreate.familyName")
            }
    )
    Family mapToEntity(FamilyRequestCreate familyRequestCreate);
}
