package com.amverma.user_service.dto;

import com.amverma.user_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DTOMapper {
    User toEntity(UserRequestDTO userRequestDTO);

    @Mapping(source = "id", target = "userId")
    UserResponseDTO toDTO(User user);

    void update(UserRequestDTO userRequestDTO, @MappingTarget User user);
}
