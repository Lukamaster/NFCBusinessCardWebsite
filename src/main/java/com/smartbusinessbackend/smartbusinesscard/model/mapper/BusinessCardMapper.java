package com.smartbusinessbackend.smartbusinesscard.model.mapper;

import com.smartbusinessbackend.smartbusinesscard.model.AppUser;
import com.smartbusinessbackend.smartbusinesscard.model.BusinessCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessCardMapper {

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    BusinessCard mapBusinessCardForUser(AppUser appUser);
}
