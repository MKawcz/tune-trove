package com.tunetrove.mapper;

import com.tunetrove.dto.AlbumDto;
import com.tunetrove.model.Album;
import com.tunetrove.response.AlbumResponse;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper implements Mapper<Album, AlbumDto, AlbumResponse> {
    @Override
    public Album toEntityFromDto(AlbumDto dto) {
        Album album = new Album();
        album.setSpotifyId(dto.getSpotifyId());
        album.setName(dto.getName());
        album.setReleaseDate(dto.getReleaseDate());
        return album;
    }

    @Override
    public Album toEntityFromResponse(AlbumResponse response) {
        return null;
    }

    @Override
    public AlbumDto toDtoFromEntity(Album album) {
        return null;
    }

    @Override
    public AlbumDto toDtoFromResponse(AlbumResponse response) {
        return null;
    }
}
