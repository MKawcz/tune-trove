package com.tunetrove.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Data
public class SongDto {
    private Long idSong;
    @JsonProperty("id")
    private String spotifyId;
    private String name;
    @JsonProperty("duration_ms")
    private Duration duration;
    private List<ImageDto> images;
    private List<ArtistDto> artists;
    private String spotifyAlbumId;
    private String type;
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
}
