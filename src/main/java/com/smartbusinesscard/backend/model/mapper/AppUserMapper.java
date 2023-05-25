package com.smartbusinesscard.backend.model.mapper;

import com.smartbusinesscard.backend.model.AppUser;
import com.smartbusinesscard.backend.model.dto.AppUserDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    @Mapping(source = "firstName",target = "firstName")
    @Mapping(source = "username",target = "username")
    @Mapping(source = "lastName",target = "lastName")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "dateOfBirth",target = "dateOfBirth")
    @Mapping(source = "countryOfResidence",target = "countryOfResidence")
    AppUserDetailsDto mapUserToDTO(AppUser user);
}
