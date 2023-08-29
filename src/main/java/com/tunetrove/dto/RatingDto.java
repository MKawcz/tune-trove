package com.tunetrove.dto;

import lombok.Data;

@Data
public class RatingDto {
    private Long idRating;
    private Double value;
    private Long userId;
    private Long reviewableId;
}
