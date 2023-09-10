package com.tunetrove.mapper;

import com.tunetrove.dto.ArtistDto;
import com.tunetrove.dto.ImageDto;
import com.tunetrove.model.Artist;
import com.tunetrove.model.Image;
import com.tunetrove.response.ArtistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ArtistMapper implements Mapper<Artist, ArtistDto> {

    private final ImageMapper imageMapper;

    @Override
    public Artist toEntityFromDto(ArtistDto dto) {
        Artist artist = new Artist();
        artist.setSpotifyId(dto.getSpotifyId());
        artist.setName(dto.getName());
        List<ImageDto> imageDtos = dto.getImages();

        return artist;
    }

    //prawdopodobnie najlepiej będzie przypisywać
    // pola z relacjami w serwisie a tu tylko te bez relacji
    // ewentualnie tylko pole image


    @Override
    public ArtistDto toDtoFromEntity(Artist artist) {
        return null;
    }
    
}
