package com.tunetrove.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SongResponse {
    @JsonProperty("id")
    private String spotifyId;
    private String name;
    @JsonProperty("duration_ms")
    private Integer durationMs;
    private List<ArtistResponse> artists;
    private AlbumResponse album;
    private String type;
    private List<ImageResponse> images;
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
}
