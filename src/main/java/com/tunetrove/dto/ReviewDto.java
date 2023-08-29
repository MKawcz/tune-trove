package com.tunetrove.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDto {
    private Long idReview;
    private String content;
    private LocalDateTime datePosted;
    private Long userId;
    private Long reviewableId;
}
