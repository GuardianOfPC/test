package com.example.test.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "fullName", source = ".", qualifiedByName = "getFullName")
    ClientDTO mapToClientDTO(ClientResponse response);

    @Named("getFullName")
    default String getFullName(ClientResponse response) {
        return response.getClientFirstName() + " " + response.getClientLastName();
    }
}
