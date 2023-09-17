package com.tunetrove.service;

import com.tunetrove.dto.ArtistDto;
import com.tunetrove.exception.ArtistNotFoundException;
import com.tunetrove.mapper.ArtistMapper;
import com.tunetrove.model.Artist;
import com.tunetrove.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final SpotifyService spotifyService;
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final ImageService imageService;
    private final TokenService tokenService;

    public ArtistDto createOrUpdateArtist(String artistId) {
        // Pobierz token dostępu do Spotify
        String accessToken = tokenService.getAccessToken();

        // Pobierz dane artysty z API Spotify
        ArtistDto spotifyArtistData = spotifyService.fetchData(
                artistId,
                "artists",
                accessToken,
                ArtistDto.class
        );

        if (spotifyArtistData == null) {
            // Obsłuż błąd, np. artysta nie istnieje
            throw new ArtistNotFoundException(artistId);
        }

        // Sprawdź, czy artysta istnieje w bazie danych
        Artist existingArtist = artistRepository.findBySpotifyId(spotifyArtistData.getSpotifyId())
                .orElse(null);

        if (existingArtist == null) {
            Artist newArtist = createArtistFromDto(spotifyArtistData);
            return artistMapper.toDtoFromEntity(newArtist);
        } else {
            // Jeśli artysta istnieje w bazie danych, zaktualizuj jego dane
            // Tutaj możesz zaktualizować inne pola artysty, takie jak obrazki, albumy itp.
            return updateArtistFromDto(existingArtist.getIdArtist(), spotifyArtistData);
        }
    }

    public ArtistDto updateArtistFromDto(long id, ArtistDto updatedArtistDto) {
        Artist existingArtist = artistRepository.findById(id).orElseThrow(() -> new ArtistNotFoundException(String.valueOf(id)));

        existingArtist.setName(updatedArtistDto.getName());
        existingArtist.setSpotifyId(updatedArtistDto.getSpotifyId());

        imageService.setArtistImageFromDto(existingArtist, updatedArtistDto);

        Artist savedArtist = artistRepository.save(existingArtist);

        return artistMapper.toDtoFromEntity(savedArtist);
    }

    // Metoda do pobierania artysty po ID
    public ArtistDto getArtistById(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(String.valueOf(id)));
        return artistMapper.toDtoFromEntity(artist);
    }

    // Metoda do pobierania wszystkich artystów
    public List<ArtistDto> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        return artists.stream()
                .map(artistMapper::toDtoFromEntity)
                .toList();
    }

    // Metoda do usuwania artysty po ID
    public void deleteArtist(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(String.valueOf(id)));
        artistRepository.delete(artist);
    }

    private Artist createArtistFromDto(ArtistDto artistDto) {
        Artist newArtist = artistMapper.toEntityFromDto(artistDto);
        return artistRepository.save(newArtist);
    }
}
