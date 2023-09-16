package com.tunetrove.mapper;

import com.tunetrove.dto.ArtistDto;
import com.tunetrove.model.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArtistMapper implements Mapper<Artist, ArtistDto> {

    private final ImageMapper imageMapper;

    @Override
    public Artist toEntityFromDto(ArtistDto dto) {
        Artist artist = new Artist();
        artist.setSpotifyId(dto.getSpotifyId());
        artist.setName(dto.getName());
        return artist;
    }

    @Override
    public ArtistDto toDtoFromEntity(Artist artist) {
        ArtistDto dto = new ArtistDto();
        dto.setSpotifyId(artist.getSpotifyId());
        dto.setName(artist.getName());

        if (artist.getArtistImage() != null) {
            dto.setImages(List.of(imageMapper.toDtoFromEntity(artist.getArtistImage())));
        }
        return dto;
    }

}
