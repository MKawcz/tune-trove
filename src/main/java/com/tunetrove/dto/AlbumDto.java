package com.tunetrove.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumDto {
    private Long idAlbum;
    @JsonProperty("id")
    private String spotifyId;
    private String name;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    private List<ArtistDto> artists;
    private List<SongDto> songs;
    @JsonProperty("album_type")
    private String albumType;
    @JsonProperty("total_tracks")
    private Integer totalTracks;
    private List<ImageDto> images;
    private List<String> genres;
    private String type;
    private String label;
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
}
