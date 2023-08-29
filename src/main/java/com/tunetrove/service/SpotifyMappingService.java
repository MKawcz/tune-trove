package com.tunetrove.service;

import com.tunetrove.dto.AlbumDto;
import com.tunetrove.dto.ArtistDto;
import com.tunetrove.dto.ImageDto;
import com.tunetrove.dto.SongDto;
import com.tunetrove.response.AlbumResponse;
import com.tunetrove.response.ArtistResponse;
import com.tunetrove.response.ImageResponse;
import com.tunetrove.response.SongResponse;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class SpotifyMappingService {
    public AlbumDto mapToAlbumDto(AlbumResponse response) {
        AlbumDto dto = new AlbumDto();

        dto.setSpotifyId(response.getSpotifyId());
        dto.setName(response.getName());
        dto.setReleaseDate(LocalDate.parse(response.getReleaseDate()));
        dto.setAlbumType(response.getAlbumType());
        dto.setTotalTracks(response.getTotalTracks());
        dto.setGenres(response.getGenres());
        dto.setType(response.getType());
        dto.setLabel(response.getLabel());
        dto.setExternalUrls(response.getExternalUrls());

        if (response.getImages() != null) {
            List<ImageDto> images = response.getImages().stream()
                    .map(this::mapToImageDto).toList();

            dto.setImages(images);
        }

        if (response.getSongs() != null) {
            List<SongDto> songs = response.getSongs().stream()
                    .map(this::mapToSongDto).toList();
            dto.setSongs(songs);
        }

        if (response.getArtists() != null) {
            List<ArtistDto> artists = response.getArtists().stream()
                    .map(this::mapToArtistDto).toList();

            dto.setArtists(artists);
        }

        return dto;
    }

    public ImageDto mapToImageDto(ImageResponse response) {
        ImageDto dto = new ImageDto();

        dto.setUrl(response.getUrl());
        dto.setHeight(response.getHeight());
        dto.setWidth(response.getWidth());

        return dto;
    }

    public SongDto mapToSongDto(SongResponse response) {
        SongDto dto = new SongDto();

        dto.setSpotifyId(response.getSpotifyId());
        dto.setName(response.getName());
        dto.setDuration(Duration.ofMillis(response.getDurationMs()));
        dto.setSpotifyAlbumId(response.getAlbum().getSpotifyId());
        dto.setExternalUrls(response.getExternalUrls());
        dto.setType(response.getType());

        if (response.getArtists() != null) {
            List<ArtistDto> artists = response.getArtists().stream()
                    .map(this::mapToArtistDto).toList();

            dto.setArtists(artists);
        }

        return dto;
    }

    public ArtistDto mapToArtistDto(ArtistResponse response) {
        ArtistDto dto = new ArtistDto();

        dto.setSpotifyId(response.getSpotifyId());
        dto.setName(response.getName());
        dto.setGenres(response.getGenres());
        dto.setType(response.getType());
        dto.setExternalUrls(response.getExternalUrls());

        if (response.getImages() != null) {
            List<ImageDto> images = response.getImages().stream()
                    .map(this::mapToImageDto).toList();

            dto.setImages(images);
        }

        return dto;
    }

}

//TODO wydziel pola do metod