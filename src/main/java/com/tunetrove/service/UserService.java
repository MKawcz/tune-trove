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

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;

    public UserDto createOrUpdateUser(OAuth2User oauth2User) {
        UserDto spotifyUserData = userMapper.toDtoFromApiResponse(oauth2User);
        imageService.setUserImageFromApiResponse(spotifyUserData, oauth2User);

        User existingUser = userRepository.findBySpotifyId(spotifyUserData.getSpotifyId())
                .orElse(null);

        if(existingUser == null) {
            User newUser = createUserFromDto(spotifyUserData);
            return userMapper.toDtoFromEntity(newUser);
        } else {
            return updateUserFromDto(existingUser.getIdUser(), spotifyUserData);
        }
    }

    public UserDto updateUserFromDto(long id, UserDto updatedUserDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Aktualizacja danych użytkownika
        existingUser.setEmailAddress(updatedUserDto.getEmailAddress());
        existingUser.setUsername(updatedUserDto.getUsername());
        existingUser.setSpotifyId(updatedUserDto.getSpotifyId());

        // Aktualizacja obrazka profilowego
        imageService.setUserImageFromDto(existingUser, updatedUserDto);

        // Zapisz zaktualizowanego użytkownika w bazie danych.
        User savedUser = userRepository.save(existingUser);

        // Zwróć zaktualizowanego użytkownika w formie UserDto.
        return userMapper.toDtoFromEntity(savedUser);
    }

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

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    private User createUserFromDto(UserDto userDto) {
        User newUser = userMapper.toEntityFromDto(userDto);
        return userRepository.save(newUser);
    }

}
