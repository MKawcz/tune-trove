package com.tunetrove.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tunetrove.response.ImageResponse;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class AlbumDto {
    private Long idAlbum;
    private String spotifyId;
    private String name;
    private LocalDate releaseDate;
    private List<ArtistDto> artists;
    private List<SongDto> songs;
    private String albumType;
    private Integer totalTracks;
    private List<ImageDto> images;
    private List<String> genres;
    private String type;
    private String label;
    private Map<String, String> externalUrls;
}
