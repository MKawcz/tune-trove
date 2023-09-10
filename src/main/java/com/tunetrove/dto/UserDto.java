package com.tunetrove.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserDto {
    private Long idUser;
    @JsonProperty("id")
    private String spotifyId;
    private String username;
    private String emailAddress;
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
    private ImageDto profileImage;
}
