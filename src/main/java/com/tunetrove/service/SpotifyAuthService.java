package com.tunetrove.service;

import com.tunetrove.dto.UserDto;
import com.tunetrove.mapper.UserMapper;
import com.tunetrove.model.User;
import com.tunetrove.repository.UserRepository;
import com.tunetrove.response.ImageResponse;
import com.tunetrove.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpotifyAuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUserOrFetchExisting(UserResponse spotifyUserData) {
        String spotifyUserId = spotifyUserData.getSpotifyId();

        User user = userRepository.findBySpotifyId(spotifyUserId)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setSpotifyId(spotifyUserId);
                    return newUser;
                });

        updateUserInfoFromSpotifyData(user, spotifyUserData);

        User savedUser = userRepository.save(user);
        return userMapper.toDtoFromEntity(savedUser);
    }

    public UserResponse mapToUserResponse(OAuth2User oauth2User) {
        return userMapper.toUserResponse(oauth2User);
    }

    private void updateUserInfoFromSpotifyData(User user, UserResponse spotifyUserData) {
        userMapper.updateUserFromResponse(user, spotifyUserData);
    }

}
