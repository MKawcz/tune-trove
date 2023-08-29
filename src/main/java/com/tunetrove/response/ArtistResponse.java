package com.tunetrove.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ArtistResponse {
    @JsonProperty("id")
    private String spotifyId;
    @JsonProperty("display_name")
    private String name;
    private List<String> genres;
    private List<ImageResponse> images;
    private String type;
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
}
