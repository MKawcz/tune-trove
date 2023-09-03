package com.tunetrove.mapper;

import com.tunetrove.dto.ArtistDto;
import com.tunetrove.model.Artist;
import com.tunetrove.response.ArtistResponse;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper implements Mapper<Artist, ArtistDto, ArtistResponse> {
    @Override
    public Artist toEntityFromDto(ArtistDto dto) {
        Artist artist = new Artist();
        artist.setSpotifyId(dto.getSpotifyId());
        artist.setName(dto.getName());
        return artist;
    }

    //prawdopodobnie najlepiej będzię przypisywać
    // pola z relacjami w serwisie a tu tylko te bez relacji
    // ewentualnie tylko pole image

    @Override
    public Artist toEntityFromResponse(ArtistResponse response) {
        return null;
    }

    @Override
    public ArtistDto toDtoFromEntity(Artist artist) {
        return null;
    }

    @Override
    public ArtistDto toDtoFromResponse(ArtistResponse response) {
        return null;
    }
}
