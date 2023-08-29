package com.tunetrove.mapper;

import com.tunetrove.dto.ImageDto;
import com.tunetrove.model.Image;
import com.tunetrove.response.ImageResponse;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper implements Mapper<Image, ImageDto, ImageResponse> {
    @Override
    public Image toEntityFromDto(ImageDto dto) {
        Image image = new Image();
        image.setUrl(dto.getUrl());
        image.setWidth(dto.getWidth());
        image.setHeight(dto.getHeight());
        return image;
    }

    @Override
    public Image toEntityFromResponse(ImageResponse response) {
        Image image = new Image();
        image.setUrl(response.getUrl());
        image.setWidth(response.getWidth());
        image.setHeight(response.getHeight());
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

    @Override
    public ImageDto toDtoFromResponse(ImageResponse response) {
        ImageDto dto = new ImageDto();
        dto.setUrl(response.getUrl());
        dto.setWidth(response.getWidth());
        dto.setHeight(response.getHeight());
        return dto;
    }
}
