package com.tunetrove.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UserResponse {
    @JsonProperty("id")
    private String spotifyId;
    private String name;
    private String email;
    private List<ImageResponse> images;
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
}
