package com.tunetrove.controller;

import com.tunetrove.dto.ArtistDto;
import com.tunetrove.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @PostMapping("/{spotifyId}")
    public ResponseEntity<ArtistDto> createOrUpdateArtist(
            @PathVariable String spotifyId) {
        return ResponseEntity.ok(artistService.createOrUpdateArtist(spotifyId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDto> updateArtist(
            @PathVariable Long id,
            @RequestBody ArtistDto updatedArtistDto) {
        return ResponseEntity.ok(artistService.updateArtistFromDto(id, updatedArtistDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.ok("Artist of id: " + id + " has been deleted");
    }
}

