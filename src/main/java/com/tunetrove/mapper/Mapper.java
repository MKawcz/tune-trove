package com.tunetrove.mapper;

import org.springframework.security.oauth2.core.user.OAuth2User;

public interface Mapper<ENTITY, DTO> {
    ENTITY toEntityFromDto(DTO dto);

    DTO toDtoFromEntity(ENTITY entity);

}
