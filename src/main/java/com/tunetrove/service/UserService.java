package com.tunetrove.service;

import com.tunetrove.dto.UserDto;
import com.tunetrove.exception.UserNotFoundException;
import com.tunetrove.mapper.UserMapper;
import com.tunetrove.model.User;
import com.tunetrove.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toDtoFromEntity(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDtoFromEntity).toList();
    }

    public UserDto updateUserFromDto(long id, UserDto updatedUserDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Aktualizacja danych użytkownika
        updateUserData(existingUser, updatedUserDto);

        // Aktualizacja obrazka profilowego
        imageService.setUserProfileImageFromDto(existingUser, updatedUserDto);

        // Zapisz zaktualizowanego użytkownika w bazie danych.
        User savedUser = userRepository.save(existingUser);

        // Zwróć zaktualizowanego użytkownika w formie UserDto.
        return userMapper.toDtoFromEntity(savedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    public UserDto createUserOrFetchExisting(OAuth2User oauth2User) {
        UserDto spotifyUserData = userMapper.toDtoFromApiResponse(oauth2User);
        imageService.setUserProfileImageFromApiResponse(spotifyUserData, oauth2User);

        String spotifyUserId = spotifyUserData.getSpotifyId();

        User user = userRepository.findBySpotifyId(spotifyUserId)
                .orElseGet(() -> createUserFromDto(spotifyUserData));

        return updateUserFromDto(user.getIdUser(), spotifyUserData);
    }

    private User createUserFromDto(UserDto userDto) {
        User newUser = new User();
        newUser.setSpotifyId(userDto.getSpotifyId());
        return userRepository.save(newUser);
    }

    private void updateUserData(User user, UserDto updatedUserDto) {
        // Tutaj umieść kod do aktualizacji danych użytkownika, np. email i nazwy użytkownika.
        user.setEmailAddress(updatedUserDto.getEmailAddress());
        user.setUsername(updatedUserDto.getUsername());
    }

}
