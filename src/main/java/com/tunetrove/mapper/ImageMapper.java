package com.tunetrove.mapper;

import com.tunetrove.dto.ImageDto;
import com.tunetrove.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageMapper implements Mapper<Image, ImageDto> {

    @Override
    public Image toEntityFromDto(ImageDto dto) {
        Image image = new Image();
        image.setUrl(dto.getUrl());
        image.setWidth(dto.getWidth());
        image.setHeight(dto.getHeight());
        return image;
    }

    @Override
    public ImageDto toDtoFromEntity(Image image) {
        ImageDto dto = new ImageDto();
        dto.setIdImage(image.getIdImage());
        dto.setUrl(image.getUrl());
        dto.setWidth(image.getWidth());
        dto.setHeight(image.getHeight());
        return dto;
    }

}
