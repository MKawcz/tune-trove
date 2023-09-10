package com.tunetrove.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ArtistDto {
    @JsonProperty("id")
    private Long idArtist;
    private String spotifyId;
    @JsonProperty("display_name")
    private String name;
    private List<AlbumDto> albums;
    private List<SongDto> songs;
    private List<ImageDto> images;
    private List<String> genres;
    private String type;
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
}
