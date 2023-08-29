package com.tunetrove.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ArtistDto {
    private Long idArtist;
    private String spotifyId;
    private String name;
    private List<AlbumDto> albums;
    private List<SongDto> songs;
    private List<ImageDto> images;
    private List<String> genres;
    private String type;
    private Map<String, String> externalUrls;
}
