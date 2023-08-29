package com.tunetrove.controller;

import com.tunetrove.dto.UserDto;
import com.tunetrove.response.UserResponse;
import com.tunetrove.service.SpotifyAuthService;
import com.tunetrove.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final SpotifyAuthService spotifyAuthService;
    private final UserService userService;

    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal OAuth2User oauth2User) {
        UserResponse userResponse = spotifyAuthService.mapToUserResponse(oauth2User);
        UserDto userDto = spotifyAuthService.createUserOrFetchExisting(userResponse);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUserDto = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok("User of id: " + id + " has been deleted");
    }
}

