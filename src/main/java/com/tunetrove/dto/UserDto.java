package com.tunetrove.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserDto {
    private Long idUser;
    private String spotifyId;
    private String username;
    private String emailAddress;
    private Map<String, String> externalUrls;
    private ImageDto profileImage;
}
