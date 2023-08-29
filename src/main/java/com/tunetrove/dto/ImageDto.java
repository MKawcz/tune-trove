package com.tunetrove.dto;

import lombok.Data;

@Data
public class ImageDto {
    private Long idImage;
    private String url;
    private Integer height;
    private Integer width;
}
