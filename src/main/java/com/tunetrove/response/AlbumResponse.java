package com.tunetrove.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AlbumResponse {
    @JsonProperty("id")
    private String spotifyId;
    private String name;
    @JsonProperty("release_date")
    private String releaseDate;
    private List<ArtistResponse> artists;
    private List<SongResponse> songs;
    @JsonProperty("album_type")
    private String albumType;
    @JsonProperty("total_tracks")
    private Integer totalTracks;
    private List<ImageResponse> images;
    private List<String> genres;
    private String type;
    private String label;
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
}

// pola które chcesz wyświetlić dodajesz tylko tu i w dto
// nie muszą być w tej samej kolejności co w api