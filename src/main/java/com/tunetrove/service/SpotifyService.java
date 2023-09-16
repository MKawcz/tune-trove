package com.tunetrove.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunetrove.dto.AlbumDto;
import com.tunetrove.dto.ArtistDto;
import com.tunetrove.dto.SongDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class SpotifyService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://api.spotify.com/v1/";

    private final ObjectMapper objectMapper;

    public <T> T fetchData(String id, String endpoint, String accessToken, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(
                BASE_URL + endpoint + "/" + id,
                HttpMethod.GET,
                entity,
                String.class
        );

        // Teraz możemy przekształcić odpowiedź JSON na obiekt responseType za pomocą Jackson.
        try {
            return objectMapper.readValue(response.getBody(), responseType);
        } catch (Exception e) {
            //TODO Obsłuż błąd parsowania
            e.printStackTrace();
            return null;
        }
    }
}

//    public AlbumDto getAlbum(String id, String accessToken) {
//        AlbumResponse albumResponse = fetchData(id, "albums", accessToken, AlbumResponse.class);
//        if (albumResponse == null) {
//            throw new RuntimeException();  //TODO handle exception
//        }
//
//        return spotifyMappingService.mapToAlbumDto(albumResponse);
//    }
//
//    public ArtistDto getArtist(String id, String accessToken) {
//        ArtistResponse artistResponse = fetchData(id, "artists", accessToken, ArtistResponse.class);
//        if (artistResponse == null) {
//            throw new RuntimeException();  //TODO handle exception
//        }
//
//        return spotifyMappingService.mapToArtistDto(artistResponse);
//    }
//
//    public SongDto getSong(String id, String accessToken) {
//        SongResponse songResponse = fetchData(id, "tracks", accessToken, SongResponse.class);
//        if (songResponse == null) {
//            throw new RuntimeException();  //TODO handle exception
//        }
//
//        return spotifyMappingService.mapToSongDto(songResponse);
//    }
