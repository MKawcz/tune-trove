package com.tunetrove.controller;

import com.tunetrove.dto.AlbumDto;
import com.tunetrove.dto.ArtistDto;
import com.tunetrove.dto.SongDto;
import com.tunetrove.service.SpotifyMappingService;
import com.tunetrove.service.SpotifyService;
import com.tunetrove.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/spotify")
public class SpotifyController {

    private final SpotifyService spotifyService;
    private final TokenService tokenService;

    @GetMapping("/albums/{id}")
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable String id) {
        String accessToken = tokenService.getAccessToken();
        AlbumDto albumDto = spotifyService.getAlbum(id, accessToken);

        return ResponseEntity.ok(albumDto);
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<ArtistDto> getArtist(@PathVariable String id) {
        String accessToken = tokenService.getAccessToken();
        ArtistDto artistDto = spotifyService.getArtist(id, accessToken);

        return ResponseEntity.ok(artistDto);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SongDto> getSong(@PathVariable String id) {
        String accessToken = tokenService.getAccessToken();
        SongDto songDto = spotifyService.getSong(id, accessToken);

        return ResponseEntity.ok(songDto);
    }

}
