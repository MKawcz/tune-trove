package com.tunetrove.mapper;

public interface Mapper<ENTITY, DTO, RESPONSE> {
    ENTITY toEntityFromDto(DTO dto);

    ENTITY toEntityFromResponse(RESPONSE response);

    DTO toDtoFromEntity(ENTITY entity);

    DTO toDtoFromResponse(RESPONSE response);

}
