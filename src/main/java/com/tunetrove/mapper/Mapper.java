package com.tunetrove.mapper;

public interface Mapper<ENTITY, DTO> {
    ENTITY toEntityFromDto(DTO dto);

    DTO toDtoFromEntity(ENTITY entity);


}
