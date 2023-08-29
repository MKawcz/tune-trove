package com.tunetrove.dto;

import lombok.Data;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Data
public class SongDto {
    private Long idSong;
    private String spotifyId;
    private String name;
    private Duration duration;
    private List<ImageDto> images;
    private List<ArtistDto> artists;
    private String spotifyAlbumId;
    private String type;
    private Map<String, String> externalUrls;
}
