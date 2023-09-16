package com.tunetrove.service;

import com.tunetrove.dto.ArtistDto;
import com.tunetrove.mapper.ArtistMapper;
import com.tunetrove.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final SpotifyService spotifyService;
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    public ArtistDto getArtistInfo(String artistId, String accessToken) {
        return spotifyService.fetchData(artistId, "artists", accessToken, ArtistDto.class);
    }
}
